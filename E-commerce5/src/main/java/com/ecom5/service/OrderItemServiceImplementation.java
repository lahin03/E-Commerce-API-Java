package com.ecom5.service;

import org.springframework.stereotype.Service;

import com.ecom5.model.OrderItem;
import com.ecom5.repository.OrderItemRepository;

@Service
public class OrderItemServiceImplementation implements OrderItemService {
	
	private OrderItemRepository orderItemRepository;

	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {

		return orderItemRepository.save(orderItem);
	}

}
