package com.example.question1libraryapi.controller.user;

import com.example.question1libraryapi.model.user.ApiResponse;
import com.example.question1libraryapi.model.user.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private List<UserProfile> users = new ArrayList<>();

    // Constructor - initialize sample users
    public UserProfileController() {
        users.add(new UserProfile(1L, "john_doe", "john@example.com", "John Doe", 28, "USA", "Software Engineer", true));
        users.add(new UserProfile(2L, "jane_smith", "jane@example.com", "Jane Smith", 26, "Canada", "Data Scientist", true));
        users.add(new UserProfile(3L, "mike_wilson", "mike@example.com", "Mike Wilson", 35, "UK", "Product Manager", true));
        users.add(new UserProfile(4L, "sarah_jones", "sarah@example.com", "Sarah Jones", 24, "USA", "Designer", false));
        users.add(new UserProfile(5L, "tom_brown", "tom@example.com", "Tom Brown", 32, "India", "DevOps Engineer", true));
    }

    // GET all users
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "All users retrieved successfully", users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                ApiResponse<UserProfile> response = new ApiResponse<>(true, "User retrieved successfully", user);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        ApiResponse<UserProfile> response = new ApiResponse<>(false, "User not found", null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Search by username
    @GetMapping("/search/username/{username}")
    public ResponseEntity<ApiResponse<UserProfile>> searchByUsername(@PathVariable String username) {
        for (UserProfile user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                ApiResponse<UserProfile> response = new ApiResponse<>(true, "User found", user);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        ApiResponse<UserProfile> response = new ApiResponse<>(false, "User not found", null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Search by country
    @GetMapping("/search/country/{country}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByCountry(@PathVariable String country) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : users) {
            if (user.getCountry().equalsIgnoreCase(country)) {
                result.add(user);
            }
        }
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "Users found", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Filter by age range
    @GetMapping("/search/age-range")
    public ResponseEntity<ApiResponse<List<UserProfile>>> filterByAgeRange(
            @RequestParam int minAge,
            @RequestParam int maxAge) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : users) {
            if (user.getAge() >= minAge && user.getAge() <= maxAge) {
                result.add(user);
            }
        }
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "Users in age range found", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // POST - Create user profile
    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUserProfile(@RequestBody UserProfile newUser) {
        users.add(newUser);
        ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile created successfully", newUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // PUT - Update user information
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUserProfile(@PathVariable Long userId, @RequestBody UserProfile updatedUser) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setUsername(updatedUser.getUsername());
                user.setEmail(updatedUser.getEmail());
                user.setFullName(updatedUser.getFullName());
                user.setAge(updatedUser.getAge());
                user.setCountry(updatedUser.getCountry());
                user.setBio(updatedUser.getBio());
                ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile updated successfully", user);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        ApiResponse<UserProfile> response = new ApiResponse<>(false, "User not found", null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // PATCH - Activate user profile
    @PatchMapping("/{userId}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activateUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(true);
                ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile activated", user);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        ApiResponse<UserProfile> response = new ApiResponse<>(false, "User not found", null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // PATCH - Deactivate user profile
    @PatchMapping("/{userId}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivateUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(false);
                ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile deactivated", user);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        ApiResponse<UserProfile> response = new ApiResponse<>(false, "User not found", null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // DELETE user profile
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUserProfile(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                users.remove(user);
                ApiResponse<String> response = new ApiResponse<>(true, "User profile deleted successfully", "User ID: " + userId);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        ApiResponse<String> response = new ApiResponse<>(false, "User not found", null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
