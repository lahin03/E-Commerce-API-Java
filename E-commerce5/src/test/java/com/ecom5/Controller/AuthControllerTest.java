package com.ecom5.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ecom5.Config.JwtProvider;
import com.ecom5.controller.AuthController;
import com.ecom5.exception.UserException;
import com.ecom5.model.Role;
import com.ecom5.model.User;
import com.ecom5.repository.UserRepository;
import com.ecom5.request.LoginRequest;
import com.ecom5.service.CustomUserServiceImplementation;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CustomUserServiceImplementation customUserService;

    @InjectMocks
    private AuthController authController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSignupSuccess() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

        User user = new User();
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setRole(Role.USER);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtProvider.generateToken(any())).thenReturn("mockToken");

        mockMvc.perform(post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testSignupFail_EmailExists() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

        User user = new User();
        user.setEmail("existinguser@example.com");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        mockMvc.perform(post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testSigninSuccess() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("testuser@example.com");
        loginRequest.setPassword("password123");

        User user = new User();
        user.setEmail("testuser@example.com");
        user.setPassword("encodedPassword");
        user.setRole(Role.USER);

        when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtProvider.generateToken(any())).thenReturn("mockToken");

        mockMvc.perform(post("/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testSigninFail_InvalidCredentials() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("wronguser@example.com");
        loginRequest.setPassword("wrongpassword");

        when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(null);

        mockMvc.perform(post("/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }
}
