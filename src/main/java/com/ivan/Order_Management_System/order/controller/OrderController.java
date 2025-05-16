package com.ivan.Order_Management_System.order.controller;

import com.ivan.Order_Management_System.order.dto.DTOOrder;
import com.ivan.Order_Management_System.order.model.Order;
import com.ivan.Order_Management_System.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody DTOOrder dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Order createdOrder = orderService.createOrder(dto, username);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getMyOrders() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Order> orders = orderService.getUserOrders(username);
        return ResponseEntity.ok(orders);
    }
}
