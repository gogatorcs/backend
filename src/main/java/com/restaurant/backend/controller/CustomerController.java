package com.restaurant.backend.controller;

import com.restaurant.backend.dto.CustomerDTO;
import com.restaurant.backend.dto.CustomerOrders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurant.backend.model.Customer;
import com.restaurant.backend.service.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
// CRUD
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    // A constructor that starts a service.
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Mapping an endpoint to a POST request to add a customer.
    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer response = customerService.addCustomer(customer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Mapping an endpoint to a GET request to get all customers.
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> response = customerService.findAllCustomers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Mapping an endpoint to a GET request to find a customer by their id.
    @GetMapping("/find/{id}")
    public Optional<Customer> getCustomerById (@PathVariable("id") Long id) {
        return customerService.findCustomerById(id);
        // return new ResponseEntity<>(response, HttpStatus.OK)
    }

    // Mapping an endpoint to a PUT request to update a customer by their id.
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO) {
        Optional<Customer> existingCustomer = customerService.findCustomerById(id);

        if (existingCustomer.isPresent()) {
            Customer customerToUpdate = existingCustomer.get();

            customerService.updateCustomerFromDTO(customerToUpdate, customerDTO);

            Customer updatedCustomer = customerService.updateCustomer(customerToUpdate);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Mapping an endpoint to a DELETE request to delete a customer by their id.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Customer with ID " + id + " has been deleted.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}