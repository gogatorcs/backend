package com.restaurant.backend.dto;
// Frontend data transfer object to update an order.
import com.fasterxml.jackson.annotation.JsonProperty;
public class OrderDTO {

    @JsonProperty("customer_id")
    private Long customer_id;

    @JsonProperty("menu_id")
    private Long menu_id;

    // Constructors
    public OrderDTO() {}

    // Getters and Setters
    public Long getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(Long customerId) {
        this.customer_id = customerId;
    }

    public Long getMenuId() {
        return menu_id;
    }

    public void setMenuId(Long menu_id) {
        this.menu_id = menu_id;
    }
}