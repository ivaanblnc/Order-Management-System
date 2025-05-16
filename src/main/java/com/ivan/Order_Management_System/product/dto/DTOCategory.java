package com.ivan.Order_Management_System.product.dto;

public class DTOCategory {
    private String name;
    private String description;

    public DTOCategory() {}

    public DTOCategory(String name, String description) {
        this.name = name;
        this.description = description;
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
}