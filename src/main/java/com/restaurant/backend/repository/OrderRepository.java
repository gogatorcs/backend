package com.restaurant.backend.repository;

import com.restaurant.backend.model.Order;
import com.restaurant.backend.dto.OrderSummary;
import com.restaurant.backend.dto.CustomerOrders;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

// A repository that inherits access to functions belonging to the Jpa Repository.
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Additional queries not provided by the JpaRepository.

    // A view of customer orders for the day.
    @Query(value = "SELECT new com.restaurant.backend.dto.CustomerOrders(o.id, c.name, o.date, m.name, m.price, o.status)" +
            "FROM Menu m JOIN Order o ON m.id = o.menuId JOIN Customer c ON o.customerId = c.id WHERE o.date = :date")
    List<CustomerOrders> findAllCustomerOrdersByDate(@Param("date") LocalDate date);

    // A query to select orders where customer ids' match.
    @Query(value = "SELECT o FROM Order o WHERE o.customerId = :id")
    List<Order> findByCustomerId(@Param("id") Long id);

    // A view of an order summary, of all orders made by a customer.
    @Query(value = "SELECT new com.restaurant.backend.dto.OrderSummary(o.id, o.date, m.name, m.price, o.status)" +
            "FROM Order o JOIN Menu m ON o.menuId = m.id WHERE o.customerId = :customer_id")
    List<OrderSummary> findSummaryFor(@Param("customer_id") Long customer_id);

    // A query to update the status of all orders that match customer ids.
    @Modifying
    @Transactional
    @Query(value = "UPDATE Order o SET o.status = :status WHERE o.customerId = :customer_id")
    int changeStatusTo(@Param("customer_id") Long customer_id, @Param("status") String status);

    // A query to select all orders where customer ids' match.
    @Query("SELECT o FROM Order o WHERE o.customerId = :customer_id")
    List<Order> findAllByCustomerId(Long customer_id);

}