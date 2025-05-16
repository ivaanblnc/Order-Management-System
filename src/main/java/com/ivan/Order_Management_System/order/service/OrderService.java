package com.ivan.Order_Management_System.order.service;

import com.ivan.Order_Management_System.auth.model.User;
import com.ivan.Order_Management_System.auth.repository.UserRepository;
import com.ivan.Order_Management_System.order.dto.DTOOrder;
import com.ivan.Order_Management_System.order.dto.DTOOrderItem;
import com.ivan.Order_Management_System.order.model.Order;
import com.ivan.Order_Management_System.order.model.OrderItem;
import com.ivan.Order_Management_System.order.repository.OrderRepository;
import com.ivan.Order_Management_System.product.model.Product;
import com.ivan.Order_Management_System.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Order createOrder(DTOOrder dto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        Order order = new Order(user, dto.getOrderDate(), dto.getTotalAmount(), dto.getStatus());
        List<OrderItem> items = new ArrayList<>();

        for (DTOOrderItem dtoItem : dto.getOrderItems()) {
            Product product = productRepository.findById(dtoItem.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

            OrderItem item = new OrderItem(product, order, dtoItem.getQuantity(), dtoItem.getPrice());
            items.add(item);
        }

        order.setOrderItems(items);
        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(String username) {
        return orderRepository.findByUserUsername(username);
    }
}
