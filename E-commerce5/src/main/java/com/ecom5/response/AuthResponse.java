package com.ecom5.response;

import java.util.List;

public class AuthResponse {

	private String jwt;
	
	private String message;
	
	private String role;
	
	public AuthResponse() {
		
	}

	public AuthResponse(String jwt, String message, String role) {
		super();
		this.jwt = jwt;
		this.message = message;
		this.role=role;
	}

	
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
