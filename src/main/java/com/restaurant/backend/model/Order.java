package com.restaurant.backend.model;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.time.LocalDate;

// An entity class that represents the order table.
@Entity
@Table(name = "`order`")
public class Order implements Serializable {
    // Represents column values found in the order table.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    private String status;

    // Class constructors.
    public Order() {}

    public Order(Long customerId, Long menuId, LocalDate date, String status) {
        this.customerId = customerId;
        this.menuId = menuId;
        this.date = date;
        this.status = status;
    }

    public Order(Long customerId, Long menuId) {
        this.customerId = customerId;
        this.menuId = menuId;
        this.date = LocalDate.now();
        this.status = "PLACED";
    }

    // Getters and Setters.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}