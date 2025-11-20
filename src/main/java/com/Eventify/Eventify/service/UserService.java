package com.Eventify.Eventify.service;

import com.Eventify.Eventify.dto.user.UserRegistrationRequest;
import com.Eventify.Eventify.dto.user.UpdateUserRequest;
import com.Eventify.Eventify.dto.user.UserResponse;
import com.Eventify.Eventify.dto.user.UpdateRoleRequest;

import java.util.List;

public interface UserService {

    UserResponse registerUser(UserRegistrationRequest dto);

    UserResponse getUserById(String id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(String id, UpdateUserRequest dto);

    UserResponse updateRole(String id, UpdateRoleRequest dto);

    void deleteUser(String id);

    UserResponse getByEmail(String email);
}
