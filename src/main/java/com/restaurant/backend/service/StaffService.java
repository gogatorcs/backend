package com.restaurant.backend.service;

import com.restaurant.backend.dto.CustomerDTO;
import com.restaurant.backend.dto.StaffDTO;
import com.restaurant.backend.model.Customer;
import com.restaurant.backend.model.Staff;
import com.restaurant.backend.repository.StaffRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffService {
    private final StaffRepository staffRepository;

    // A service to allow access to functions responsible for handling the staff database.
    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    // A function to add a staff member to the staff database.
    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    // A function to find all staff member in the staff database.
    public List<Staff> findAllStaff() {
        return staffRepository.findAllStaff();
    }

    // A function to update a staff member in the staff database.
    public Staff updateStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    // A function to find a staff member by id in the staff database.
    public Optional<Staff> findStaffById(Long id) {
        return staffRepository.findById(id);
    }

    // A function to delete a staff member by their id in the staff database.
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    public void updateStaffFromDTO(Staff staff, StaffDTO staffDTO) {
        staff.setName(staffDTO.getName());
        staff.setEmail(staffDTO.getEmail());
        staff.setPassword(staffDTO.getPassword());
    }
}