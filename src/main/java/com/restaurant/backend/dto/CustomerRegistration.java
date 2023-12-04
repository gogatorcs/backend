package com.restaurant.backend.dto;
// Frontend data transfer object to register a new customer.
public class CustomerRegistration {
    private String name;
    private String email;
    private String password;

    public CustomerRegistration() {}

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
