package com.example.springrestapi.response;

import com.example.springrestapi.dto.UserDto;
import com.example.springrestapi.request.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private List<UserDto> userResponseList;
    private int currentPage;
    private int size;
    private int totalPage;
}
