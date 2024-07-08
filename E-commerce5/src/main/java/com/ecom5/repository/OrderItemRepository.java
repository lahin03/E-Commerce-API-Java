package com.ecom5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom5.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
