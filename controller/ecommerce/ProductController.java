package com.example.question1libraryapi.controller.ecommerce;

import com.example.question1libraryapi.model.ecommerce.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    // Constructor - initialize 10 sample products
    public ProductController() {
        products.add(new Product(1L, "Laptop", "High-performance laptop", 999.99, "Electronics", 5, "Dell"));
        products.add(new Product(2L, "Wireless Mouse", "Comfortable wireless mouse", 29.99, "Electronics", 50, "Logitech"));
        products.add(new Product(3L, "USB-C Cable", "Fast charging USB-C cable", 9.99, "Electronics", 100, "Anker"));
        products.add(new Product(4L, "Running Shoes", "Professional running shoes", 89.99, "Sports", 20, "Nike"));
        products.add(new Product(5L, "Sports Watch", "Smart sports watch", 199.99, "Electronics", 15, "Garmin"));
        products.add(new Product(6L, "Yoga Mat", "Non-slip yoga mat", 24.99, "Sports", 30, "Generic"));
        products.add(new Product(7L, "T-Shirt", "Cotton t-shirt", 19.99, "Clothing", 100, "Nike"));
        products.add(new Product(8L, "Jeans", "Classic blue jeans", 49.99, "Clothing", 40, "Levi's"));
        products.add(new Product(9L, "Coffee Maker", "Automatic coffee maker", 79.99, "Home", 10, "Philips"));
        products.add(new Product(10L, "Desk Lamp", "LED desk lamp", 39.99, "Home", 0, "IKEA"));
    }

    // GET all products (with pagination)
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        // Simple pagination logic
        int start = (page - 1) * limit;
        int end = Math.min(start + limit, products.size());
        List<Product> paginatedProducts = products.subList(start, end);
        return new ResponseEntity<>(paginatedProducts, HttpStatus.OK);
    }

    // GET product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                result.add(product);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // GET products by brand
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getBrand().equalsIgnoreCase(brand)) {
                result.add(product);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Search products by keyword
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                product.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(product);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // GET products within price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                result.add(product);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // GET in-stock products
    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getStockQuantity() > 0) {
                result.add(product);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // POST - Add new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product newProduct) {
        products.add(newProduct);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    // PUT - Update product
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setName(updatedProduct.getName());
                product.setDescription(updatedProduct.getDescription());
                product.setPrice(updatedProduct.getPrice());
                product.setCategory(updatedProduct.getCategory());
                product.setStockQuantity(updatedProduct.getStockQuantity());
                product.setBrand(updatedProduct.getBrand());
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // PATCH - Update stock quantity
    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStockQuantity(@PathVariable Long productId, @RequestParam int quantity) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setStockQuantity(quantity);
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                products.remove(product);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
