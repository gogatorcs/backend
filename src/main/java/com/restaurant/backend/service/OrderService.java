package com.restaurant.backend.service;

import com.restaurant.backend.dto.CustomerOrders;
import com.restaurant.backend.dto.OrderDTO;
import com.restaurant.backend.dto.OrderSummary;
import com.restaurant.backend.model.Customer;
import com.restaurant.backend.model.Menu;
import com.restaurant.backend.repository.CustomerRepository;
import com.restaurant.backend.repository.MenuRepository;
import com.restaurant.backend.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurant.backend.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    // A service that allows access to functions responsible for handling orders.
    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
    }

    // A function that takes an order, and saves it to the database.
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
    // A function that takes an order, and saves it to the database.
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    // A function that finds all orders.
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    // A function to find all customer orders for the day.
    public List<CustomerOrders> findAllCustomerOrdersForTheDay() {
        LocalDate date = LocalDate.now();
        return orderRepository.findAllCustomerOrdersByDate(date);
    }

    // A function that finds orders by a customer's id.
    public List<Order> findOrderByCustomerId(Long id) {
        return orderRepository.findByCustomerId(id);
    }

    // A function that returns an order summary by a customer's id.
    public List<OrderSummary> findOrderSummaryByCustomerId(Long customer_id) {
        return orderRepository.findSummaryFor(customer_id);
    }

    // A function to change the status of a customer's orders.
    public int changeCustomerOrderStatus(Long customer_id, String status) {
        return orderRepository.changeStatusTo(customer_id, status);
    }

    // A function to delete all orders by customer's id.
    public void deleteOrdersByCustomerId(Long customer_id) {
        List<Order> orders = orderRepository.findAllByCustomerId(customer_id);
        orderRepository.deleteAll(orders);
    }

    // A function that deletes an order by their id.
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order with ID " + id + " not found."));
        orderRepository.delete(order);
    }
}