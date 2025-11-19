package com.Eventify.Eventify.service.Impl;

import com.Eventify.Eventify.dto.UserRegistrationRequest;
import com.Eventify.Eventify.dto.UpdateUserRequest;
import com.Eventify.Eventify.enums.Role;
import com.Eventify.Eventify.model.User;
import com.Eventify.Eventify.exception.UserNotFoundException;
import com.Eventify.Eventify.exception.UsernameAlreadyExistsException;
import com.Eventify.Eventify.repository.UserRepository;
import com.Eventify.Eventify.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(UserRegistrationRequest dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UsernameAlreadyExistsException("Email already exists: " + dto.getEmail());
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.ROLE_USER); // default role
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(String id, UpdateUserRequest dto) {
        User user = getUserById(id);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return userRepository.save(user);
    }

    @Override
    public User updateRole(String id, Role role) {
        User user = getUserById(id);
        user.setRole(role); // enum ensures valid role
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Cannot delete. User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email " + email));
    }
}
