package com.example.springrestapi.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AvatarRequest {
    @NotBlank(message = "Avatar is required")
    private String avatarString;
}
