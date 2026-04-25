package com.airbnb.userservice.validation;

import com.airbnb.userservice.dto.UserRequestDTO;

public class UserValidation {

    public static void validateUser(UserRequestDTO request) {

        if (request.getName() == null || request.getName().isEmpty()) {
            throw new RuntimeException("Name cannot be empty");
        }

        if (request.getEmail() == null || !request.getEmail().contains("@")) {
            throw new RuntimeException("Invalid email format");
        }

        if (request.getPassword() == null || request.getPassword().length() < 4) {
            throw new RuntimeException("Password must be at least 4 characters");
        }
    }
}