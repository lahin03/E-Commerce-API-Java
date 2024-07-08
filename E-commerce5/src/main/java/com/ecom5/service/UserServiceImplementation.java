package com.ecom5.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom5.Config.JwtProvider;
import com.ecom5.exception.UserException;
import com.ecom5.model.User;
import com.ecom5.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	private UserRepository userRepository;
	
	private JwtProvider jwtProvider;
	
	public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
	}
	
	@Override
	public User findUserById(Long userId) throws UserException {
		
		Optional<User>user = userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UserException("user not found with id: " + userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		
		String email = jwtProvider.getEmailFromToken(jwt);
		
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("User not found with email: " + email);
		}
		
		return user;
	}

}
