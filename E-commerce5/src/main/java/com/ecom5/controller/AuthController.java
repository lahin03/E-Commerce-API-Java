package com.ecom5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecom5.Config.JwtProvider;
import com.ecom5.exception.UserException;
import com.ecom5.model.User;
import com.ecom5.repository.UserRepository;
import com.ecom5.request.LoginRequest;
import com.ecom5.response.AuthResponse;
import com.ecom5.service.CustomUserServiceImplementation;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private UserRepository userRepository;
	
	private JwtProvider jwtProvider;
	
	private PasswordEncoder passwordEncoder;
	
	private CustomUserServiceImplementation customUserService;
	
	public AuthController(UserRepository userRepository,
			CustomUserServiceImplementation customUserService,
			PasswordEncoder passwordEncoder,JwtProvider jwtProvider) {
		this.customUserService = customUserService; 
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
	}

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws UserException{
		String email = user.getEmail();
		String password = user.getPassword();
		String firstname = user.getFirstName();
		String lastname  = user.getLastName();
		
		User isEmailExist = userRepository.findByEmail(email);
		
		if(isEmailExist!=null) {
			throw new UserException("Email is Already Used With Another Account");
		}
		
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstname);
		createdUser.setLastName(lastname);
		
		User savedUser = userRepository.save(createdUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Signup Process");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse>loginUserHandler(@RequestBody LoginRequest loginRequest){
		
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Signin Process");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
	}
	
	private Authentication authenticate(String username,String password) {
		UserDetails userDetails = customUserService.loadUserByUsername(username);
		
				if(userDetails==null) {
					throw new BadCredentialsException("invalid Username");
				}
				
				if(!passwordEncoder.matches(password, userDetails.getPassword())) {
					throw new BadCredentialsException("Invalid Password");
				}
				
				return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
}

