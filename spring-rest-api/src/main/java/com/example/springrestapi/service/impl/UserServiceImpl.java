package com.example.springrestapi.service.impl;


import com.example.springrestapi.repository.UserRepository;
import com.example.springrestapi.dto.UserDto;
import com.example.springrestapi.exception.PasswordChangeException;
import com.example.springrestapi.exception.ResourceNotFoundException;
import com.example.springrestapi.entity.User;
import com.example.springrestapi.request.AvatarRequest;
import com.example.springrestapi.request.PasswordRequest;
import com.example.springrestapi.request.UserRequest;
import com.example.springrestapi.response.NewPasswordResponse;
import com.example.springrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());

    }

    @Override
    public UserDto getUserByID(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user"));

        return convertToUserDto(user);
    }


    @Override
    public List<UserDto> searchUserByName(String name) {
        List<User> users = userRepository.findUserByNameContainingIgnoreCase(name);
        return users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }



    @Override
    public UserDto createUser(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .address(userRequest.getAddress())
                .avatar(userRequest.getAvatar())
                .password(userRequest.getPassword())
                .build();

        userRepository.save(user);

        return convertToUserDto(user);

    }

    @Override
    public UserDto updateUser(int id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user"));

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());
        user.setAvatar(user.getAvatar());
        user.setPassword(user.getPassword());

        userRepository.save(user);
        return convertToUserDto(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user"));

        userRepository.deleteById(id);

    }

    @Override
    public void changeUserAvatar(int id, AvatarRequest avatarRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user"));

        user.setAvatar(avatarRequest.getAvatar());
        userRepository.save(user);
    }

    @Override
    public void changeUserPassword(int id, PasswordRequest passwordRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user"));

        if(!user.getPassword().equals(passwordRequest.getOldPassword()) && !passwordRequest.getNewPassword().equals(passwordRequest.getOldPassword()) ) {
            throw new PasswordChangeException("Invalid password change request");
        }else{
            user.setPassword(passwordRequest.getNewPassword());
            userRepository.save(user);
        }
    }

    @Override
    public NewPasswordResponse forgotPassword(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user"));

        String newPassword = generateRandomPassword(user.getPassword());
        user.setPassword(newPassword);
        userRepository.save(user);

        return new NewPasswordResponse(newPassword);
    }

    private String generateRandomPassword(String userPassword) {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        int passwordLength = userPassword.length();

        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

    private UserDto convertToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getAvatar()
        );
    }
}


