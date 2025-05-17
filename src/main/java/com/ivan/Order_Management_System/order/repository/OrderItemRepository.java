package com.ivan.Order_Management_System.order.repository;

import com.ivan.Order_Management_System.order.model.OrderItem;
import com.ivan.Order_Management_System.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    boolean existsByProduct(Product product);
}
