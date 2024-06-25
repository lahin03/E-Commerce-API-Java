package com.ecom5.service;

import com.ecom5.exception.UserException;
import com.ecom5.model.User;

public interface UserService {

	public User finduserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
}
  