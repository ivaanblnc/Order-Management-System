package com.ivan.Order_Management_System.auth.dto;

public class DTOUserLogin {
    private String username;
    private String password;

    public DTOUserLogin() { }

    public DTOUserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
