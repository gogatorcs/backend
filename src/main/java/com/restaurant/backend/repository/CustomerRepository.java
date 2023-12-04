package com.restaurant.backend.repository;

import com.restaurant.backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// A repository that inherits access to functions belonging to the Jpa Repository.
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Additional queries not provided by the JpaRepository.

    // A query that finds all customers from the database.
    @Query(value = "SELECT c FROM Customer c")
    List<Customer> findCustomers();

    // A query that selects a customer by a customer's id from the customer's table.

    @Query(value = "SELECT c FROM Customer c WHERE c.id = :id")
    Customer findCustomerById(@Param("id") Long id);

    // A query that finds if the database has a customer with the same email and password as the passed in parameter.
    @Query(value = "SELECT c FROM Customer c WHERE c.email = :email AND c.password = :password")
    Customer findCustomerByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    // A query to select customer's with matching email address.
    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    Customer findCustomerByEmail(@Param("email") String email);

    // A query to insert a new customer into the customer table.
    @Query(value = "INSERT INTO Customer VALUES (:id, :name, :email, :password)", nativeQuery = true)
    Customer addNewCustomer(@Param("id") Long id, @Param("name") String name, @Param("email") String email, @Param("password") String password);
}