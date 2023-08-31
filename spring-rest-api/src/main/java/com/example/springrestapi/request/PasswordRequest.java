package com.example.springrestapi.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PasswordRequest {
     @NotBlank(message = "Old Password is required")
     @Size(min = 8, max = 15, message = "Password length must be between 8 and 15 characters")
     private String oldPassword;
     @NotBlank(message = "New Password is required")
     @Size(min = 8, max = 15, message = "Password length must be between 8 and 15 characters")
     private String newPassword;
}
