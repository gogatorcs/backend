package com.restaurant.backend.dto;
// Frontend transfer object to verify an actor's credentials.
public class AuthenticationRequest {
    private String email;
    private String password;

    // Constructors
    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
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

    // toString, hashCode, equals methods can be added as needed
}
