package com.airbnb.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airbnb.userservice.dto.LoginRequestDTO;
import com.airbnb.userservice.dto.LoginResponseDTO;
import com.airbnb.userservice.dto.UserDTO;
import com.airbnb.userservice.dto.UserRequestDTO;
import com.airbnb.userservice.entity.User;
import com.airbnb.userservice.repository.UserRepository;
import com.airbnb.userservice.validation.UserValidation;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;
	@Override
	public UserDTO registerUser(UserRequestDTO request) {
		UserValidation.validateUser(request);
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(com.airbnb.userservice.entity.Role.USER);
		user.setVerified(false);

		User savedUser = userRepository.save(user);

		UserDTO dto = new UserDTO();
		dto.setId(savedUser.getId());
		dto.setName(savedUser.getName());
		dto.setEmail(savedUser.getEmail());
		dto.setRole(savedUser.getRole().name());
		dto.setVerified(savedUser.isVerified());

		return dto;
	}
	

	@Override
	public LoginResponseDTO loginUser(LoginRequestDTO request){

	    User user = userRepository.findByEmail(request.getEmail())
	            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

	    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
	        throw new RuntimeException("Invalid email or password");
	    }

	    // generate token
	    String token = com.airbnb.userservice.utility.JwtUtil.generateToken(user.getEmail());

	    // return token response
	    return new LoginResponseDTO(token);
	}
	@Override
	public UserDTO getUserProfile(String email) {

	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    UserDTO dto = new UserDTO();
	    dto.setId(user.getId());
	    dto.setName(user.getName());
	    dto.setEmail(user.getEmail());
	    dto.setRole(user.getRole().name());
	    dto.setVerified(user.isVerified());

	    return dto;
	}
}