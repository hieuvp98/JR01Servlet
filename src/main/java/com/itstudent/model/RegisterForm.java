package com.itstudent.model;

public class RegisterForm {

    private String username;

    private String password;

    private String name;

    public String getUsername() {
        return username;
    }

    public RegisterForm setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterForm setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegisterForm setName(String name) {
        this.name = name;
        return this;
    }
}
