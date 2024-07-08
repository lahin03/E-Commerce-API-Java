package com.ecom5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom5.exception.UserException;
import com.ecom5.model.Role;
import com.ecom5.model.User;
import com.ecom5.repository.UserRepository;
import com.ecom5.response.AuthResponse;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api/authtest")
public class AuthTestController {
	
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	public AuthTestController(PasswordEncoder passwordEncoder,
			UserRepository userRepository) {
		
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@GetMapping("/user_endpoint")
	//@PreAuthorize("hasRole('USER')")
	public String UserEndpoint() {
		return "User Endpoint";
	}
	
	@GetMapping("/admin_endpoint")
	//@PreAuthorize("hasRole('ADMIN')")
	public String AdminEndpoint() {
		return "Admin Endpoint";
	}
	
	@GetMapping("/all_endpoint")
	public String All() {
		return "All";
	}
	
	@PostMapping("/add_admin")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AuthResponse>createAdmin(@RequestBody User user) throws UserException{
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
		createdUser.setRole(Role.ADMIN);
		
		User savedUser = userRepository.save(createdUser);
		
		//System.out.println(user.getFirstName());
		
//		Authentication authentication = new UsernamePasswordAuthenticationToken(
//                savedUser.getEmail(), savedUser.getPassword(), AuthorityUtils.createAuthorityList(savedUser.getRole().toString())
//        );
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		
//		String token = jwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse();
		authResponse.setMessage("New Admin added");
		authResponse.setRole("ADMIN");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
	}
}
