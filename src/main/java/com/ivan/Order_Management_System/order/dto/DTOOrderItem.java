package com.ivan.Order_Management_System.order.dto;


import java.math.BigDecimal;

public class DTOOrderItem {
    private int id;
    private int productId;
    private int orderId;
    private int quantity;
    private BigDecimal price;

    public DTOOrderItem() {}

    public DTOOrderItem(int productId, int orderId, int quantity, BigDecimal price) {
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}