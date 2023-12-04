package com.restaurant.backend.service;

import com.restaurant.backend.model.Admin;
import com.restaurant.backend.model.Customer;
import com.restaurant.backend.model.Staff;
import com.restaurant.backend.repository.AdminRepository;
import com.restaurant.backend.repository.CustomerRepository;
import com.restaurant.backend.repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final AdminRepository adminRepository;

    // A service that allows access to a customer repository, a staff repository, and an admin repository.
    @Autowired
    public AuthenticationService(CustomerRepository customerRepository, StaffRepository staffRepository, AdminRepository adminRepository) {
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
        this.adminRepository = adminRepository;
    }

    // A function to register and add a new customer to the customer database.
    public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // A function to verify a customer exists in the database by their email and password.
    public boolean authenticateCustomer(String email, String password) {
        Customer customer = customerRepository.findCustomerByEmailAndPassword(email, password);
        return customer != null;
    }

    // A function to verify a staff member exists in the database by their email and password.
    public boolean authenticateStaff(String email, String password) {
        Staff staff = staffRepository.findStaffByEmailAndPassword(email, password);
        return staff != null;
    }

    // A function to verify an admin exists in the database by their email and password.
    public boolean authenticateAdmin(String email, String password) {
        Admin admin = adminRepository.findAdminByEmailAndPassword(email, password);
        return admin != null;
    }
}