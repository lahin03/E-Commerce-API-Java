package com.ecom5.service;

import com.ecom5.exception.CartItemException;
import com.ecom5.exception.UserException;
import com.ecom5.model.Cart;
import com.ecom5.model.CartItem;
import com.ecom5.model.Product;

public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;
	
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
	
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
