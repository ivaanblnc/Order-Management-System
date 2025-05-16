package com.ivan.Order_Management_System.product.service;

import com.ivan.Order_Management_System.order.repository.OrderItemRepository;
import com.ivan.Order_Management_System.product.controller.ProductDeletionException;
import com.ivan.Order_Management_System.product.dto.DTOProduct;
import com.ivan.Order_Management_System.product.model.Category;
import com.ivan.Order_Management_System.product.model.Product;
import com.ivan.Order_Management_System.product.repository.CategoryRepository;
import com.ivan.Order_Management_System.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product createProduct(DTOProduct dto) {
        Category category = categoryRepository.findByName(dto.getCategory().getName())
                .orElseGet(() -> {
                    Category newCat = new Category(dto.getCategory().getName(), dto.getCategory().getDescription());
                    return categoryRepository.save(newCat);
                });

        Product product = new Product(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getStock(), category);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    }

    public void deleteProduct(int id) {

        Product product = getProductById(id);

        if (!productRepository.existsById(id)) {
            throw new ProductDeletionException("Producto no encontrado");
        }
        if (orderItemRepository.existsByProduct(product)) {
            throw new ProductDeletionException("No se puede eliminar el producto porque está en uso en una orden");
        }
        productRepository.deleteById(id);
    }
}
