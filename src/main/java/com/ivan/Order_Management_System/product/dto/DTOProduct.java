package com.ivan.Order_Management_System.product.dto;


import java.math.BigDecimal;
import java.util.Objects;

public class DTOProduct {

    private String name;

    private String description;

    private BigDecimal price;

    private int stock;

    private DTOCategory category;

    public DTOProduct() {
    }

    public DTOProduct(String name, String description, BigDecimal price, int stock, DTOCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }


    public DTOCategory getCategory() {
        return category;
    }

    public void setCategory(DTOCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DTOProduct)) return false;
        DTOProduct that = (DTOProduct) o;
        return stock == that.stock &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, stock);
    }
}
