package com.restaurant.backend.service;

import com.restaurant.backend.dto.BrowseMenu;
import com.restaurant.backend.dto.MenuDTO;
import com.restaurant.backend.repository.MenuRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurant.backend.model.Menu;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuService {
    private final MenuRepository menuRepository;

    // A service to allow access to functions responsible for handling the menu.
    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // A function that takes a menu item, and saves it to the database.
    public Menu addMenuItem(Menu menu) {
        return menuRepository.save(menu);
    }

    // A function that finds all menu items.
    public List<Menu> findAllMenuItems() { return menuRepository.findAll(); }

    // A function that creates a view of a menu.
    public List<BrowseMenu> browseMenu() { return menuRepository.findAllMenu(); }

    // A function that updates a menu item.
    public Menu updateMenuItem(Menu menu) { return menuRepository.save(menu); }

    // A function that returns an optional, to find a menu item by its id.
    public Optional<Menu> findMenuItemById(Long id) {
        return menuRepository.findById(id);
    }

    // A function to find a menu item by its id.
    public Menu findMenuById(Long id) {
        return menuRepository.findByMenuId(id);
    }

    // A function to find all menu items by a category.
    public List<Menu> findMenuItemsByCategory(String category) {
        return menuRepository.findByCategory(category);
    }

    // A function to delete a menu item by its id.
    public void deleteMenuItem(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Menu Item with ID " + id + " not found."));
        menuRepository.deleteById(id);
    }

    public void updateMenuFromDTO(Menu menu, MenuDTO menuDTO) {
        menu.setName(menuDTO.getName());
        menu.setDescription(menuDTO.getDescription());
        menu.setPrice(menuDTO.getPrice());
        menu.setCategory(menuDTO.getCategory());
    }
}