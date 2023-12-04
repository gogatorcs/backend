package com.restaurant.backend.controller;

import com.restaurant.backend.dto.AdminDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurant.backend.model.Admin;
import com.restaurant.backend.service.AdminService;

import java.util.List;
import java.util.Optional;

// CRUD
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    // A constructor to start a service.
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Mapping an endpoint to a POST request to add a new admin.
    @PostMapping("/add")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        Admin newAdmin = adminService.addAdmin(admin);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    // Mapping an endpoint to a GET request to get all admins.
    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.findAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    // Mapping an endpoint to a GET request to find an admin by their id.
    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Admin>> getAdminById(@PathVariable("id") Long id) {
        Optional<Admin> admin = adminService.findAdminById(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    // Mapping an endpoint to a PUT request to update an admin by their id.
    @PutMapping("/update/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") Long id, @RequestBody AdminDTO adminDTO) {
        Optional<Admin> existingAdmin = adminService.findAdminById(id);

        if(existingAdmin.isPresent()) {
            Admin adminToUpdate = existingAdmin.get();
            adminService.updateAdminFromDTO(adminToUpdate, adminDTO);

            Admin updatedAdmin = adminService.updateAdmin(adminToUpdate);
            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Mapping an endpoint to a DELETE request to delete an admin by their id.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") Long id) {
        adminService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}