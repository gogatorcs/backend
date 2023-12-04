package com.restaurant.backend.service;

import com.restaurant.backend.dto.AdminDTO;
import com.restaurant.backend.dto.CustomerDTO;
import com.restaurant.backend.model.Admin;
import com.restaurant.backend.model.Customer;
import com.restaurant.backend.repository.AdminRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminService {
    private final AdminRepository adminRepository;

    // A service that allows access to a admin repository.
    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // A function that adds an admin to the admin database.
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // A function that finds all admins located in the admin database.
    public List<Admin> findAllAdmins() { return adminRepository.findAdmins(); }

    // A function that updates an admin found in the admin database.
    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // A function that finds an admin by their id.
    public Optional<Admin> findAdminById(Long id) {
        return adminRepository.findById(id);
    }

    // A function that deletes an admin by their id.
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public void updateAdminFromDTO(Admin admin, AdminDTO adminDTO) {
        admin.setName(adminDTO.getName());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());
    }

}