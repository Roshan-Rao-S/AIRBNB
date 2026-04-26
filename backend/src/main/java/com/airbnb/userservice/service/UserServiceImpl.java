package com.airbnb.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.airbnb.userservice.dto.LoginRequestDTO;
import com.airbnb.userservice.dto.LoginResponseDTO;
import com.airbnb.userservice.dto.UserDTO;
import com.airbnb.userservice.dto.UserRequestDTO;
import com.airbnb.userservice.entity.User;
import com.airbnb.userservice.exception.BadRequestException;
import com.airbnb.userservice.exception.ResourceNotFoundException;
import com.airbnb.userservice.exception.UnauthorizedException;
import com.airbnb.userservice.repository.UserRepository;
import com.airbnb.userservice.utility.JwtUtil;
import com.airbnb.userservice.validation.UserValidation;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private final UserRepository userRepository;
	private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	@Autowired
	public UserServiceImpl(
			UserRepository userRepository,
			org.springframework.security.crypto.password.PasswordEncoder passwordEncoder,
			JwtUtil jwtUtil
	) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public UserDTO registerUser(UserRequestDTO request) {
		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new BadRequestException("Email already exists");
		}

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

		logger.info("Registered user with email {}", savedUser.getEmail());
		return dto;
	}
	

	@Override
	public LoginResponseDTO loginUser(LoginRequestDTO request){

	    User user = userRepository.findByEmail(request.getEmail())
	            .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

	    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
	        throw new UnauthorizedException("Invalid email or password");
	    }

	    String token = jwtUtil.generateToken(user.getEmail());

	    logger.info("User logged in: {}", user.getEmail());
	    return new LoginResponseDTO(token);
	}
	@Override
	public UserDTO getUserProfile(String email) {

	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

	    UserDTO dto = new UserDTO();
	    dto.setId(user.getId());
	    dto.setName(user.getName());
	    dto.setEmail(user.getEmail());
	    dto.setRole(user.getRole().name());
	    dto.setVerified(user.isVerified());

	    logger.debug("Fetched profile for {}", email);
	    return dto;
	}
}
