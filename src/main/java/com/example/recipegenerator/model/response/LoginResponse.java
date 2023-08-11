package com.example.recipegenerator.model.response;

import com.example.recipegenerator.entity.Role;
import lombok.*;

import java.util.List;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LoginResponse {
    private String email;
    private Role role;
    private String message;
}
