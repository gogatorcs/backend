package com.restaurant.backend.repository;

import com.restaurant.backend.model.Customer;
import com.restaurant.backend.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// A repository that inherits access to functions belonging to the Jpa Repository.
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    // Additional queries not provided by the JpaRepository.

    // A query to select all staff members from the staff table.
    @Query(value = "SELECT s FROM Staff s")
    List<Staff> findAllStaff();

    // A query to select a staff member by their id.
    @Query(value = "SELECT s FROM Staff s WHERE s.id = :id")
    Staff findStaffById(@Param("id") Long id);

    // A query to select a staff member where email addresses match.
    @Query("SELECT s FROM Staff s WHERE s.email = :email")
    Staff findStaffByEmail(@Param("email") String email);


    // A query to select a staff member where an email and password are found in the staff table.
    @Query(value = "SELECT s FROM Staff s WHERE s.email = :email AND s.password = :password")
    Staff findStaffByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}