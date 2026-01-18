package com.example.foodordering.controller;

import com.example.foodordering.model.Order;
import com.example.foodordering.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Order createOrder(@RequestParam String customerName) {
        return service.createOrder(customerName);
    }

    @PostMapping("/{orderId}/items")
    public String addItem(@PathVariable Long orderId,
                          @RequestParam Long menuItemId,
                          @RequestParam int quantity) {
        service.addItem(orderId, menuItemId, quantity);
        return "Item added to order";
    }

    @PostMapping("/{orderId}/complete")
    public Order complete(@PathVariable Long orderId) {
        return service.completeOrder(orderId);
    }
}

