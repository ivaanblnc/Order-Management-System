package com.ivan.Order_Management_System.auth.dto;

public class DTORole {

    private String name;
    private String description;

    public DTORole(String name) {
        this.name = name;
        this.description = null;
    }

    public DTORole(String name, String description) {
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
