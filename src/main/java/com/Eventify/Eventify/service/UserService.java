package com.Eventify.Eventify.service;

import com.Eventify.Eventify.dto.user.UserRegistrationRequest;
import com.Eventify.Eventify.dto.user.UpdateUserRequest;
import com.Eventify.Eventify.enums.Role;
import com.Eventify.Eventify.model.User;

import java.util.List;

public interface UserService {

    User registerUser(UserRegistrationRequest dto);

    User getUserById(String id);

    List<User> getAllUsers();

    User updateUser(String id, UpdateUserRequest dto);

    User updateRole(String id, Role role);

    void deleteUser(String id);

    User getByEmail(String email);
}
