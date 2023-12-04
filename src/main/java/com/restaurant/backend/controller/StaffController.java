package com.restaurant.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurant.backend.dto.StaffDTO;
import com.restaurant.backend.model.Staff;
import com.restaurant.backend.service.StaffService;

import java.util.List;
import java.util.Optional;
// CRUD
@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;

    // A constructor that starts a service.
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // Mapping an endpoint to a POST request to add a new staff member.
    @PostMapping("/add")
    public ResponseEntity<Staff> addCustomer(@RequestBody Staff staff) {
        Staff new_staff = staffService.addStaff(staff);
        return new ResponseEntity<>(new_staff, HttpStatus.CREATED);
    }

    // Mapping an endpoint to a GET request to get all staff members.
    @GetMapping("/all")
    public ResponseEntity<List<Staff>> getAllStaff() {
        List<Staff> staff = staffService.findAllStaff();
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    // Mapping an endpoint to a GET request to find a staff member with an id.
    @GetMapping("/find/{id}")
    public Optional<Staff> getCustomerById (@PathVariable("id") Long id) {
        return staffService.findStaffById(id);
        // return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    // Mapping an endpoint to a PUT request to update a staff by their id.
    @PutMapping("/update/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable("id") Long id, @RequestBody StaffDTO staffDTO) {
        Optional<Staff> existingStaff = staffService.findStaffById(id);

        if (existingStaff.isPresent()) {
            Staff staffToUpdate = existingStaff.get();
            staffService.updateStaffFromDTO(staffToUpdate, staffDTO);

            Staff updatedStaff = staffService.updateStaff(staffToUpdate);
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Mapping an endpoint to a DELETE request to delete a staff member by their id.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        staffService.deleteStaff(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}