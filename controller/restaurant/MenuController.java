package com.example.question1libraryapi.controller.restaurant;

import com.example.question1libraryapi.model.restaurant.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private List<MenuItem> menuItems = new ArrayList<>();

    // Constructor - initialize 8 sample menu items
    public MenuController() {
        menuItems.add(new MenuItem(1L, "Spring Rolls", "Crispy vegetable spring rolls", 5.99, "Appetizer", true));
        menuItems.add(new MenuItem(2L, "Garlic Bread", "Toasted bread with garlic butter", 4.99, "Appetizer", true));
        menuItems.add(new MenuItem(3L, "Grilled Salmon", "Fresh salmon with herbs", 15.99, "Main Course", true));
        menuItems.add(new MenuItem(4L, "Pasta Carbonara", "Classic Italian pasta", 12.99, "Main Course", true));
        menuItems.add(new MenuItem(5L, "Chocolate Cake", "Rich chocolate dessert", 6.99, "Dessert", true));
        menuItems.add(new MenuItem(6L, "Ice Cream", "Vanilla ice cream", 4.99, "Dessert", false));
        menuItems.add(new MenuItem(7L, "Iced Tea", "Refreshing iced tea", 2.99, "Beverage", true));
        menuItems.add(new MenuItem(8L, "Coffee", "Espresso coffee", 3.49, "Beverage", true));
    }

    // GET all menu items
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    // GET menu item by ID
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET items by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getItemsByCategory(@PathVariable String category) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // GET only available items
    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailableItems(@RequestParam(defaultValue = "true") boolean available) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.isAvailable() == available) {
                result.add(item);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Search menu items by name
    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchByName(@RequestParam String name) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(item);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // POST - Add new menu item
    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem newItem) {
        menuItems.add(newItem);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    // PUT - Toggle item availability
    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable());
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE menu item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                menuItems.remove(item);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
