package com.restaurant.backend.repository;

import com.restaurant.backend.dto.BrowseMenu;
import com.restaurant.backend.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// A repository that inherits access to functions belonging to the Jpa Repository.
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    // Additional queries not provided by the JpaRepository.

    // A query to view a modified version of a menu.
    @Query(value = "SELECT new com.restaurant.backend.dto.BrowseMenu(m.name, m.description, m.price, m.category) " +
            "FROM Menu m")
    List<BrowseMenu> findAllMenu();

    // A query to select menu items with a matching id.
    @Query(value = "SELECT m FROM Menu m WHERE m.id = :id")
    Menu findByMenuId(@Param("id") Long id);

    // A query to select menu items with matching categories.
    @Query(value = "SELECT m FROM Menu m WHERE m.category = :category")
    List<Menu> findByCategory(@Param("category") String category);

}