package com.example.springrestapi.service.impl;


import com.example.springrestapi.dao.UserDAO;
import com.example.springrestapi.dto.UserDto;
import com.example.springrestapi.exception.PasswordChangeException;
import com.example.springrestapi.exception.ResourceNotFoundException;
import com.example.springrestapi.model.User;
import com.example.springrestapi.request.AvatarRequest;
import com.example.springrestapi.request.PasswordRequest;
import com.example.springrestapi.request.UserRequest;
import com.example.springrestapi.response.NewPasswordResponse;
import com.example.springrestapi.response.UserResponse;
import com.example.springrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserResponse getUsers(int page, int limit) {
        List<User> users = userDAO.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .address(user.getAddress())
                    .avatar(user.getAvatar())
                    .build();

            userDtos.add(userDto);
        }
        int totalPage = (int) Math.ceil((double) userDtos.size() / limit);

        return new UserResponse(userDtos, page, limit, totalPage );
    }

    @Override
    public UserDto getUserByID(int id) {
        User user = userDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user"));

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getAvatar()
        );
    }


    @Override
    public List<UserDto> searchUserByName(String name) {
        List<User> users = userDAO.findAll();

        return users.stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .map(user -> new UserDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getAddress(),
                        user.getAvatar()
                ))
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

        userDAO.save(user);

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getAvatar()
        );

    }

    @Override
    public UserDto updateUser(int id, UserRequest userRequest) {
        User user = userDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user"));

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());
        user.setAvatar(user.getAvatar());
        user.setPassword(user.getPassword());


        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getAvatar()
        );
    }

    @Override
    public void deleteUser(int id) {
        userDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user"));

        userDAO.deleteById(id);

    }

    @Override
    public void changeUserAvatar(int id, AvatarRequest avatarRequest) {
        User user = userDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user"));

        user.setAvatar(avatarRequest.getAvatarString());
    }

    @Override
    public void changeUserPassword(int id, PasswordRequest passwordRequest) {
        User user = userDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user"));

        if(!user.getPassword().equals(passwordRequest.getOldPassword()) && passwordRequest.getNewPassword().equals(passwordRequest.getOldPassword()) ) {
            throw new PasswordChangeException("Invalid password change request");
        }else{
            user.setPassword(passwordRequest.getNewPassword());
        }
    }

    @Override
    public NewPasswordResponse forgotPassword(int id) {
        User user = userDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user"));

        String newPassword = generateRandomPassword(user.getPassword());
        user.setPassword(newPassword);

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
}


