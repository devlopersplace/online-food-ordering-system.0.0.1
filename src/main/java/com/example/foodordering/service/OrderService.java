package com.example.foodordering.service;

import org.springframework.stereotype.Service;

import com.example.foodordering.exception.ResourceNotFoundException;
import com.example.foodordering.model.*;
import com.example.foodordering.repository.*;

@Service //it tells spring boot this class holds business logic keep an instance of it in a ready to use position. 
public class OrderService {
	

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderService(OrderRepository orderRepository,OrderItemRepository orderItemRepository,MenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.menuItemRepository = menuItemRepository;
    }
//Starts a new blank order in the database.
    public Order createOrder(String customerName) { //We are making this method inside Order.java with name createOrder
        Order order = new Order(); //Object made
        order.setCustomerName(customerName);
        return orderRepository.save(order);
    }
//we are taking about Order ID, Food ID, Qty that Links a specific food item to an existing order.
    public void addItem(Long orderId, Long menuItemId, int quantity) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order not found with id: " + orderId));
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(()-> new ResourceNotFoundException("Menu item not found with id: " + menuItemId));
        OrderItem item = new OrderItem(order, menuItem, quantity);
        orderItemRepository.save(item);
    }
//Marks the order as finished/done.ś
    public Order completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        order.setStatus("COMPLETED");
        return orderRepository.save(order);
    }
}
