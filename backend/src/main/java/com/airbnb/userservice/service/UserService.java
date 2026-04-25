package com.airbnb.userservice.service;

import com.airbnb.userservice.dto.LoginRequestDTO;
import com.airbnb.userservice.dto.LoginResponseDTO;
import com.airbnb.userservice.dto.UserDTO;
import com.airbnb.userservice.dto.UserRequestDTO;

public interface UserService {

    UserDTO registerUser(UserRequestDTO request);

    LoginResponseDTO loginUser(LoginRequestDTO request);

    UserDTO getUserProfile(String email); // ✅ ADD THIS
}