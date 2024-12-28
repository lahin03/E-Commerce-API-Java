package com.ecom5.controller;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.ecom5.Config.JwtProvider;
import com.ecom5.exception.UserException;
import com.ecom5.model.Role;
import com.ecom5.model.User;
import com.ecom5.repository.UserRepository;
import com.ecom5.request.LoginRequest;
import com.ecom5.response.AuthResponse;
import com.ecom5.service.CustomUserServiceImplementation;
import com.ecom5.service.UserService;


@Controller
@RequestMapping("/auth")
public class AuthController {
	
	private UserRepository userRepository;
	
	private JwtProvider jwtProvider;
	
	private PasswordEncoder passwordEncoder;
	
	private CustomUserServiceImplementation customUserService;
	
	private UserService userService;
	
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
			System.out.println("\n\n\n" + isEmailExist + "\n\n\n");
			throw new UserException("Email is Already Used With Another Account");
		}
		
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstname);
		createdUser.setLastName(lastname);
		createdUser.setRole(Role.USER);
		
		
		User savedUser = userRepository.save(createdUser);
		
		//System.out.println(user.getFirstName());
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(), savedUser.getPassword(), AuthorityUtils.createAuthorityList(savedUser.getRole().toString())
        );
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Signup Process");
		authResponse.setRole("USER");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
	}
	
//	@PostMapping("/add_admin")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<AuthResponse>createAdmin(@RequestBody User user) throws UserException{
//		String email = user.getEmail();
//		String password = user.getPassword();
//		String firstname = user.getFirstName();
//		String lastname  = user.getLastName();
//		
//		User isEmailExist = userRepository.findByEmail(email);
//		
//		if(isEmailExist!=null) {
//			throw new UserException("Email is Already Used With Another Account");
//		}
//		
//		User createdUser = new User();
//		createdUser.setEmail(email);
//		createdUser.setPassword(passwordEncoder.encode(password));
//		createdUser.setFirstName(firstname);
//		createdUser.setLastName(lastname);
//		createdUser.setRole(Role.ADMIN);
//		
//		User savedUser = userRepository.save(createdUser);
//		
//		//System.out.println(user.getFirstName());
//		
////		Authentication authentication = new UsernamePasswordAuthenticationToken(
////                savedUser.getEmail(), savedUser.getPassword(), AuthorityUtils.createAuthorityList(savedUser.getRole().toString())
////        );
////		SecurityContextHolder.getContext().setAuthentication(authentication);
////		
////		String token = jwtProvider.generateToken(authentication);
//
//		AuthResponse authResponse = new AuthResponse();
//		authResponse.setMessage("New Admin added");
//		authResponse.setRole("ADMIN");
//		
//		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
//	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse>loginUserHandler(@RequestBody LoginRequest loginRequest){
		
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		System.out.println("\n\n" + email + "\n\n");
		
		User user = userRepository.findByEmail(email);
	
		
		Authentication authentication = authenticate(email,password,AuthorityUtils.createAuthorityList(user.getRole().toString()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Signin Process");
		authResponse.setRole(user.getRole().toString());
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
	}
	
	private Authentication authenticate(String email,String password, List<GrantedAuthority> list) {
		
				User user = userRepository.findByEmail(email);
		
				if(user==null) {
					throw new BadCredentialsException("invalid Username");
				}
				
				if(!passwordEncoder.matches(password, user.getPassword())) {
					throw new BadCredentialsException("Invalid Password");
				}
				
				return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
	}
}