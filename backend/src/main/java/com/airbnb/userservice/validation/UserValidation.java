package com.airbnb.userservice.validation;

import com.airbnb.userservice.dto.UserRequestDTO;
import com.airbnb.userservice.exception.BadRequestException;

public class UserValidation {

    public static void validateUser(UserRequestDTO request) {

        if (request.getName() == null || request.getName().isEmpty()) {
            throw new BadRequestException("Name cannot be empty");
        }

        if (request.getEmail() == null || !request.getEmail().contains("@")) {
            throw new BadRequestException("Invalid email format");
        }

        if (request.getPassword() == null || request.getPassword().length() < 4) {
            throw new BadRequestException("Password must be at least 4 characters");
        }
    }
}
