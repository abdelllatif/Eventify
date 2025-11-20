package com.Eventify.Eventify.service.Impl;

import com.Eventify.Eventify.dto.user.UserRegistrationRequest;
import com.Eventify.Eventify.dto.user.UpdateUserRequest;
import com.Eventify.Eventify.dto.user.UserResponse;
import com.Eventify.Eventify.dto.user.UpdateRoleRequest;
import com.Eventify.Eventify.enums.Role;
import com.Eventify.Eventify.exception.UserNotFoundException;
import com.Eventify.Eventify.exception.UsernameAlreadyExistsException;
import com.Eventify.Eventify.mapper.UserMapper;
import com.Eventify.Eventify.model.User;
import com.Eventify.Eventify.repository.UserRepository;
import com.Eventify.Eventify.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }


    // ===================== REGISTER ======================
    @Override
    public UserResponse registerUser(UserRegistrationRequest dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UsernameAlreadyExistsException("Email already exists: " + dto.getEmail());
        }

        // map DTO to entity
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.ROLE_USER); // default role

        // save first -> MongoDB generates ObjectId
        User savedUser = userRepository.save(user);

        // now map saved entity to DTO
        return userMapper.toDto(savedUser);
    }



    // ===================== GET USER BY ID ======================
    @Override
    public UserResponse getUserById(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id " + id));

        return userMapper.toDto(user);
    }


    // ===================== GET ALL USERS ======================
    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }


    // ===================== UPDATE USER ======================
    @Override
    public UserResponse updateUser(String id, UpdateUserRequest dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id " + id));

        // check email uniqueness
        userRepository.findByEmail(dto.getEmail())
                .filter(existingUser -> !existingUser.getId().equals(id))
                .ifPresent(existing -> {
                    throw new UsernameAlreadyExistsException("Email already exists: " + dto.getEmail());
                });

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        return userMapper.toDto(userRepository.save(user));
    }


    // ===================== UPDATE ROLE ======================
    @Override
    public UserResponse updateRole(String id, UpdateRoleRequest dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id " + id));

        Role newRole = Role.valueOf(dto.getRole());
        user.setRole(newRole);

        return userMapper.toDto(userRepository.save(user));
    }


    // ===================== DELETE USER ======================
    @Override
    public void deleteUser(String id) {

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Cannot delete. User not found with id " + id);
        }

        userRepository.deleteById(id);
    }


    // ===================== GET USER BY EMAIL ======================
    @Override
    public UserResponse getByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with email " + email));

        return userMapper.toDto(user);
    }
}
