package com.lawencon.jobportal.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.user.LoginRequest;
import com.lawencon.jobportal.model.request.user.RegisterUserRequest;
import com.lawencon.jobportal.model.request.user.UpdateUserRequest;
import com.lawencon.jobportal.model.response.user.ListUserResponse;
import com.lawencon.jobportal.model.response.user.UserResponse;
import com.lawencon.jobportal.persistence.entity.User;

public interface UserService
        extends CrudService<User, RegisterUserRequest, UpdateUserRequest, UserResponse> {
    Optional<User> login(LoginRequest request);

    Page<ListUserResponse> getAll(PagingRequest pagingRequest, String inquiry);

    UserDetailsService userDetailsService();

}
