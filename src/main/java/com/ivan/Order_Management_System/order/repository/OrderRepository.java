package com.ivan.Order_Management_System.order.repository;


import com.ivan.Order_Management_System.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserUsername(String username);
}
