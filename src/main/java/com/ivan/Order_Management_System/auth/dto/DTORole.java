package com.ivan.Order_Management_System.auth.dto;

public class DTORole {
    private int id;
    private String name;

    public DTORole() {}

    public DTORole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
