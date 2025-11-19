package com.Eventify.Eventify.service;

import com.Eventify.Eventify.dto.UserRegistrationRequest;
import com.Eventify.Eventify.dto.UpdateUserRequest;
import com.Eventify.Eventify.enums.Role;
import com.Eventify.Eventify.model.User;

import java.util.List;

public interface UserService {

    // Register a new user (default role ROLE_USER)
    User registerUser(UserRegistrationRequest dto);

    // Get user by ID
    User getUserById(String id);

    // Get all users
    List<User> getAllUsers();

    // Update user's profile (name/email)
    User updateUser(String id, UpdateUserRequest dto);

    // Update user's role (ADMIN can do this)
    User updateRole(String id, Role role);

    // Delete user
    void deleteUser(String id);

    // Get user by email (for login/authentication)
    User getByEmail(String email);
}
