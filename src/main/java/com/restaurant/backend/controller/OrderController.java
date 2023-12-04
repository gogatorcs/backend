package com.restaurant.backend.controller;

import com.restaurant.backend.dto.CustomerOrders;
import com.restaurant.backend.dto.CustomerRegistration;
import com.restaurant.backend.dto.OrderDTO;
import com.restaurant.backend.dto.OrderSummary;
import com.restaurant.backend.model.Customer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurant.backend.model.Order;
import com.restaurant.backend.service.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
// CRUD
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    // A constructor that starts a service.
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Mapping an endpoint to a POST request to add a new order.
    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@RequestBody OrderDTO orderDTO) {

        Order new_order = new Order(orderDTO.getCustomerId(), orderDTO.getMenuId());
        Order response = orderService.createOrder(new_order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Mapping an endpoint to a POST request to add a new order.
    @PostMapping("/add/{customer_id}/{menu_id}")
    public ResponseEntity<Order> addOrder(@PathVariable("customer_id") Long customer_id, @PathVariable("menu_id") Long menu_id) {
        Order new_order = new Order(customer_id, menu_id);
        Order response = orderService.addOrder(new_order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Mapping an endpoint to a GET request to get all orders.
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> response = orderService.findAllOrders();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Mapping an endpoint to a GET request to get all orders for the day.
    @GetMapping("/get/today")
    public ResponseEntity<List<CustomerOrders>> getAllCustomerOrders() {
        List<CustomerOrders> response = orderService.findAllCustomerOrdersForTheDay();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Mapping an endpoint to a GET request to find all customer orders by their id.
    @GetMapping("/find/customer/{id}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable("id") Long id) {
        List<Order> response = orderService.findOrderByCustomerId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Mapping an endpoint to a GET request to return an order summary for a customer by their id.
    @GetMapping("/find/summary/{customer_id}")
    public ResponseEntity<List<OrderSummary>> getOrderSummaryByCustomerId(@PathVariable("customer_id") Long customer_id) {
        List<OrderSummary> summary = orderService.findOrderSummaryByCustomerId(customer_id);
        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

    // Mapping an endpoint to a POST request to change the status of a customer's order.
    @PostMapping("/update/{customer_id}/{status}")
    public ResponseEntity<?> updateCustomerOrdersStatus(@PathVariable("customer_id") Long customer_id, @PathVariable("status") String status) {
        try {
            int updatedCount = orderService.changeCustomerOrderStatus(customer_id, status);
            if (updatedCount > 0) {
                return ResponseEntity.ok().body("Updated " + updatedCount + " orders.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No orders found for customer.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    // Mapping an endpoint to a DELETE request to delete all orders made by a customer.
    @DeleteMapping("/delete/customer/{customer_id}")
    public ResponseEntity<?> deleteOrdersByCustomerId(@PathVariable("customer_id") Long customer_id) {
        try {
            orderService.deleteOrdersByCustomerId(customer_id);
            return ResponseEntity.ok().body("Orders for customer with ID " + customer_id + " have been deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with ID " + customer_id + " not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    // Mapping an endpoint to a DELETE request to delete an order by its id.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable("id") Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok().body("Order with ID " + id + " has been deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
}