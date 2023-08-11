package com.example.recipegenerator.service.impl;

import com.example.recipegenerator.entity.UserCredential;
import com.example.recipegenerator.entity.constant.ERole;
import com.example.recipegenerator.model.request.AuthRequest;
import com.example.recipegenerator.model.response.LoginResponse;
import com.example.recipegenerator.model.response.RegisterResponse;
import com.example.recipegenerator.repository.AdminRepository;
import com.example.recipegenerator.repository.UserCredentialRepository;
import com.example.recipegenerator.repository.UserRepository;
import com.example.recipegenerator.service.AuthService;
import com.example.recipegenerator.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final UserCredentialRepository userCredentialRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse registerUser(AuthRequest request) {
        return register(request, ERole.ROLE_USER);
    }

    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {
        return register(request, ERole.ROLE_ADMIN);
    }

    private RegisterResponse register(AuthRequest request, ERole roleType) {
        try {
            UserCredential userCredential = new UserCredential();
            userCredential.setEmail(request.getEmail());
            userCredential.setPassword(passwordEncoder.encode(request.getPassword()));
            userCredential.setRole(roleService.getRoleByType(roleType));
            userCredentialRepository.save(userCredential);

            if (roleType == ERole.ROLE_USER) {
                userRepository.registerUser(request.getEmail(), userCredential.getId());
            } else if (roleType == ERole.ROLE_ADMIN) {
                adminRepository.registerAdmin(request.getEmail(), userCredential.getId());
            }
            return RegisterResponse.builder().email(userCredential.getEmail()).build();
        } catch (DataIntegrityViolationException exception){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exist");
        }
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        Optional<UserCredential> userCredentialOptional = userCredentialRepository.findByEmail(request.getEmail());
        if (userCredentialOptional.isEmpty()) {
            return LoginResponse.builder()
                    .email(request.getEmail())
                    .message("User not found")
                    .build();
        }

        UserCredential userCredential = userCredentialOptional.get();
        if (!passwordEncoder.matches(request.getPassword(), userCredential.getPassword())) {
            return LoginResponse.builder()
                    .email(request.getEmail())
                    .message("Invalid password")
                    .build();
        }

        // Return successful login response
        return LoginResponse.builder()
                .email(request.getEmail())
                .role(userCredential.getRole())
                .message("Login successful")
                .build();
    }
}
