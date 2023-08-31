package com.example.springrestapi.service;

import com.example.springrestapi.dto.UserDto;
import com.example.springrestapi.model.User;
import com.example.springrestapi.request.AvatarRequest;
import com.example.springrestapi.request.PasswordRequest;
import com.example.springrestapi.request.UserRequest;
import com.example.springrestapi.response.NewPasswordResponse;
import com.example.springrestapi.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getUsers(int page, int limit);
    UserDto getUserByID(int id);

    List<UserDto> searchUserByName(String name);

    UserDto createUser(UserRequest userRequest);
    
    UserDto updateUser(int id, UserRequest userRequest);
    
    
    void deleteUser(int id);
    
    void changeUserAvatar(int id, AvatarRequest avatarRequest);
    
    void changeUserPassword(int id, PasswordRequest passwordRequest);

    NewPasswordResponse forgotPassword(int id);
    


}
