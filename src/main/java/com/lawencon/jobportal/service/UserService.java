package com.lawencon.jobportal.service;

import com.lawencon.jobportal.model.request.user.CreateUserRequest;
import com.lawencon.jobportal.model.request.user.LoginRequest;
import com.lawencon.jobportal.model.request.user.RegisterUserRequest;
import com.lawencon.jobportal.persistence.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    Optional<User> login(LoginRequest request);

    void createUser(CreateUserRequest request);

    UserDetailsService userDetailsService();

    void registerUser(RegisterUserRequest request);
}
