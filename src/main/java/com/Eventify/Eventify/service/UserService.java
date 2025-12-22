package com.Eventify.Eventify.service;

import com.Eventify.Eventify.dto.user.UserRegistrationRequest;
import com.Eventify.Eventify.dto.user.UpdateUserRequest;
import com.Eventify.Eventify.dto.user.UserResponse;
import com.Eventify.Eventify.dto.user.UpdateRoleRequest;

import java.util.List;

public interface UserService {

    UserResponse registerUser(UserRegistrationRequest dto);

    UserResponse getById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, UpdateUserRequest dto);

    UserResponse updateRole(Long id, UpdateRoleRequest dto);

    void deleteUser(Long id);

    UserResponse getByEmail(String email);
}
