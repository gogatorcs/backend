package com.restaurant.backend.dto;
// Backend request to produce a view of customer orders.
import java.math.BigDecimal;
import java.time.LocalDate;
public class CustomerOrders {
    private Long order_id;
    private String name;
    private LocalDate order_date;
    private String menu_item_name;
    private BigDecimal menu_item_price;
    private String order_status;
    public CustomerOrders(Long order_id, String name, LocalDate order_date, String menu_item_name, BigDecimal menu_item_price, String order_status) {
        this.order_id = order_id;
        this.name = name;
        this.order_date = order_date;
        this.menu_item_name = menu_item_name;
        this.menu_item_price = menu_item_price;
        this.order_status = order_status;
    }
    public Long getOrderId() {
        return order_id;
    }

    public void setOrderId(Long order_id) {
        this.order_id = order_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getOrderDate() {
        return order_date;
    }

    public void setOrderDate(LocalDate order_date) {
        this.order_date = order_date;
    }

    public String getMenuItemName() {
        return menu_item_name;
    }

    public void setMenuItemName(String menu_item_name) {
        this.menu_item_name = menu_item_name;
    }

    public BigDecimal getMenuItemPrice() {
        return menu_item_price;
    }

    public void setMenuItemPrice(BigDecimal menu_item_price) {
        this.menu_item_price = menu_item_price;
    }

    public String getOrderStatus() {
        return order_status;
    }

    public void setOrderStatus(String order_status) {
        this.order_status = order_status;
    }
}