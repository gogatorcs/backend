package com.restaurant.backend.controller;

import com.restaurant.backend.dto.AuthenticationRequest;
import com.restaurant.backend.repository.AdminRepository;
import com.restaurant.backend.repository.CustomerRepository;
import com.restaurant.backend.repository.StaffRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurant.backend.model.Admin;
import com.restaurant.backend.model.Customer;
import com.restaurant.backend.model.Staff;
import com.restaurant.backend.dto.CustomerRegistration;

import com.restaurant.backend.service.AuthenticationService;
// CRUD
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final AdminRepository adminRepository;

    // A constructor that starts services.
    public AuthenticationController(AuthenticationService authenticationService, CustomerRepository customerRepository, StaffRepository staffRepository, AdminRepository adminRepository) {
        this.authenticationService = authenticationService;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
        this.adminRepository = adminRepository;

    }

    // Mapping an end point to a POST request to register a new customer.
    @PostMapping("/register")
    public ResponseEntity<Customer> registerNewCustomer(@RequestBody CustomerRegistration registrationDto) {
        Customer new_customer = new Customer(registrationDto.getName(), registrationDto.getEmail(), registrationDto.getPassword());
        Customer response = authenticationService.registerCustomer(new_customer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Mapping an end point to a POST request to verify a customer's email and password.
    @PostMapping("/customer/verify")
    public ResponseEntity<Customer> authenticateCustomer(@RequestBody AuthenticationRequest authRequest) {
        boolean isAuthenticated = authenticationService.authenticateCustomer(authRequest.getEmail(), authRequest.getPassword());
        if (isAuthenticated) {
            Customer customer = customerRepository.findCustomerByEmail(authRequest.getEmail());
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // Mapping an end point to a POST request to verify a staff member's email and password.
    @PostMapping("/staff/verify")
    public ResponseEntity<Staff> authenticateStaff(@RequestBody AuthenticationRequest authRequest) {
        boolean isAuthenticated = authenticationService.authenticateStaff(authRequest.getEmail(), authRequest.getPassword());
        if (isAuthenticated) {
            Staff staff = staffRepository.findStaffByEmail(authRequest.getEmail());
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // Mapping an end point to a POST request to verify an admin email and password.
    @PostMapping("/admin/verify")
    public ResponseEntity<Admin> authenticateAdmin(@RequestBody AuthenticationRequest authRequest) {
        boolean isAuthenticated = authenticationService.authenticateAdmin(authRequest.getEmail(), authRequest.getPassword());
        if (isAuthenticated) {
            Admin admin = adminRepository.findAdminByEmail(authRequest.getEmail());
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}