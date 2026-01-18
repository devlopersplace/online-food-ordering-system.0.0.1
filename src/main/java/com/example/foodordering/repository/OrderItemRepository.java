package com.example.foodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodordering.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}