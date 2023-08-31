package com.example.springrestapi.controller;


import com.example.springrestapi.dto.UserDto;
import com.example.springrestapi.request.AvatarRequest;
import com.example.springrestapi.request.PasswordRequest;
import com.example.springrestapi.request.UserRequest;
import com.example.springrestapi.response.NewPasswordResponse;
import com.example.springrestapi.response.UserResponse;
import com.example.springrestapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping
    public ResponseEntity<UserResponse> getUsers(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        UserResponse userResponse = userService.getUsers(page, limit);

        return ResponseEntity.ok(userResponse);

    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> getUserByName(@RequestParam String name) {
        List<UserDto> userDtos = userService.searchUserByName(name);

        return ResponseEntity.ok(userDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        UserDto userDto = userService.getUserByID(id);

        return ResponseEntity.ok(userDto);
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserDto userDto = userService.createUser(userRequest);

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable int id, @Valid @RequestBody UserRequest userRequest) {
        UserDto userDto = userService.updateUser(id, userRequest);

        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/update-avatar")
    public ResponseEntity<?> updateAvatar(@PathVariable int id, @Valid @RequestBody AvatarRequest avatarRequest){
        userService.changeUserAvatar(id, avatarRequest);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}/update-password")
    public ResponseEntity<Void> updatePassword(@PathVariable int id, @Valid @RequestBody PasswordRequest passwordRequest) {
        userService.changeUserPassword(id, passwordRequest);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/forgot-password")
    public ResponseEntity<NewPasswordResponse> forgotPassword(@PathVariable int id) {
        NewPasswordResponse newPassword = userService.forgotPassword(id);
        return ResponseEntity.ok(newPassword);
    }

}
