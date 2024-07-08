package com.ecom5.service;

import java.util.List;

import com.ecom5.exception.OrderException;
import com.ecom5.model.Address;
import com.ecom5.model.Order;
import com.ecom5.model.User;

public interface OrderService {

	public Order createOrder(User user, Address shippingAdress);
	
	public Order findOrderById(Long orderId) throws OrderException;
	
	public List<Order>usersorderHistory(Long userId);
	
	public Order placedOrder(Long userId) throws OrderException;
	
	public Order confirmedOrder(Long orderId) throws OrderException;
	
	public Order shippedOrder(Long orderId) throws OrderException;
	
	public Order deliveredOrder(Long orderId) throws OrderException;
	
	public Order cancelledOrder(Long orderId) throws OrderException;
	
	public List<Order>getAllOrders();
	
	public void deleteOrder(Long orderId) throws OrderException;
}
