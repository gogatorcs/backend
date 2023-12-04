package com.restaurant.backend.repository;

import com.restaurant.backend.model.Admin;
import com.restaurant.backend.model.Customer;
import com.restaurant.backend.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// A repository that inherits access functions belonging to the Jpa Repository.
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Additional queries not provided by the JpaRepository.

    // A query to select all admins from the database.
    @Query(value = "SELECT * FROM Admin", nativeQuery = true)
    List<Admin> findAdmins();

    // A query to select an admin from the database by their id.
    @Query(value = "SELECT a FROM Admin a WHERE a.id = :id")
    Admin findAdminById(@Param("id") Long id);

    // A query to find an admin where an email address matches.
    @Query("SELECT a FROM Admin a WHERE a.email = :email")
    Admin findAdminByEmail(@Param("email") String email);

    // A query to verify if an admin is found in the database by their email and password.
    @Query(value = "SELECT a FROM Admin a WHERE a.email = :email AND a.password = :password")
    Admin findAdminByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}