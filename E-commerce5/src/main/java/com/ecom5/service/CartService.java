package com.ecom5.service;

import com.ecom5.exception.ProductException;
import com.ecom5.model.Cart;
import com.ecom5.model.User;
import com.ecom5.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
}
