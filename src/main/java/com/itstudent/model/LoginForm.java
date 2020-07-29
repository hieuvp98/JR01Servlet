package com.itstudent.model;

public class LoginForm {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public LoginForm setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginForm setPassword(String password) {
        this.password = password;
        return this;
    }
}
