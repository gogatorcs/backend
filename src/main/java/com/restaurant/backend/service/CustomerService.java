package com.restaurant.backend.service;

import com.restaurant.backend.dto.CustomerDTO;
import com.restaurant.backend.dto.MenuDTO;
import com.restaurant.backend.model.Menu;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurant.backend.model.Customer;
import com.restaurant.backend.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    // A service to allow access to functions responsible for handling the customer table.
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // A function to add a customer to the customer table.
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // A function to find all customers in the customer table.
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    // A function to update a customer in the customer table.
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // A function to find a customer by their id.
    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // A function to delete a customer from the customer table.
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public void updateCustomerFromDTO(Customer customer, CustomerDTO customerDTO) {
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
    }
}