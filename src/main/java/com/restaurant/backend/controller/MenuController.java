package com.restaurant.backend.controller;

import com.restaurant.backend.dto.BrowseMenu;
import com.restaurant.backend.dto.MenuDTO;
import com.restaurant.backend.model.Staff;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurant.backend.model.Menu;
import com.restaurant.backend.service.MenuService;
import com.restaurant.backend.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
// CRUD
@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    // A constructor that starts a service.
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // Mapping an endpoint to a POST request to add a new menu item.
    @PostMapping("/add")
    public ResponseEntity<Menu> addNewMenuItem (@RequestBody Menu menu) {
        Menu new_item = menuService.addMenuItem(menu);
        return new ResponseEntity<>(new_item, HttpStatus.OK);
    }

    // Mapping an endpoint to a GET request to get all menu items.
    @GetMapping("/all")
    public ResponseEntity<List<Menu>> getMenu() {
        List<Menu> response = menuService.findAllMenuItems();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Mapping an endpoint to a GET request to browse a menu.
    @GetMapping("/browse")
    public ResponseEntity<List<BrowseMenu>> browseMenu() {
        List<BrowseMenu> response = menuService.browseMenu();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Mapping an endpoint to a PUT request to update an existing menu item.
    @PutMapping("/update/{id}")
    public ResponseEntity<Menu> updateMenuItem(@PathVariable("id") Long id, @RequestBody MenuDTO menuDTO) {
        Optional<Menu> existingItem = menuService.findMenuItemById(id);

        if (existingItem.isPresent()) {
            Menu menuToUpdate = existingItem.get();
            menuService.updateMenuFromDTO(menuToUpdate, menuDTO);

            Menu updatedMenu = menuService.updateMenuItem(menuToUpdate);
            return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
    }

    // Mapping an endpoint to a GET request to find a menu item by id.
    @GetMapping("/find/{id}")
    public ResponseEntity<Menu> getMenuItemById (@PathVariable("id") Long id) {
        Menu response = menuService.findMenuById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Mapping an endpoint to a GET request to find menu items by a certain category.
    @GetMapping("/find/category/{category}")
    public ResponseEntity<List<Menu>> getMenuItemByCategory (@PathVariable("category") String category) {
        List<Menu> response = menuService.findMenuItemsByCategory(category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Mapping an endpoint to a DELETE request to delete a menu item by id.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable("id") Long id) {
        try {
            menuService.deleteMenuItem(id);
            return ResponseEntity.ok().body("Menu Item with ID " + id + " has been deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

}

