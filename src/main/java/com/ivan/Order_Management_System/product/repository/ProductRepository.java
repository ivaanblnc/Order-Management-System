package com.ivan.Order_Management_System.product.repository;

import com.ivan.Order_Management_System.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
