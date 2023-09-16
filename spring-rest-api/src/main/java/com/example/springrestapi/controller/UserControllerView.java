package com.example.springrestapi.controller;

import com.example.springrestapi.dto.UserDto;
import com.example.springrestapi.request.AvatarRequest;
import com.example.springrestapi.request.PasswordRequest;
import com.example.springrestapi.request.UserRequest;
import com.example.springrestapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Validated
@RequestMapping("/users")
public class UserControllerView {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUsers(Model model) {
        List<UserDto> userDtos = userService.getUsers();
        model.addAttribute("users", userDtos);
        return "index";
    }


    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        UserDto userDto = userService.getUserByID(id);
        model.addAttribute("user", userDto);
        return "detail";
    }

    @GetMapping("/create")
    public String createUserForm() {
        return "create";
    }


}
