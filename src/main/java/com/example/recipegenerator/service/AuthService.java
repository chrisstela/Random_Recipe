package com.example.recipegenerator.service;

import com.example.recipegenerator.model.request.AuthRequest;
import com.example.recipegenerator.model.response.LoginResponse;
import com.example.recipegenerator.model.response.RegisterResponse;

public interface AuthService {
    //Register User
    RegisterResponse registerUser(AuthRequest request);

    //Register Admin
    RegisterResponse registerAdmin(AuthRequest request);

    //Login
    LoginResponse login(AuthRequest request);

}
