package com.ivan.Order_Management_System.auth.dto;

public class DTOUser {
    private String username;
    private String email;
    private String fullName;
    private String roleName;
    private String password;

    public DTOUser() {}

    public DTOUser(String username, String email, String fullName, String roleName, String password) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.roleName = roleName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
