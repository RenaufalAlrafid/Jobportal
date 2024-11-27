package com.lawencon.jobportal.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.lawencon.jobportal.model.request.LoginRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.RegisterUserRequest;
import com.lawencon.jobportal.model.request.UpdateUserRequest;
import com.lawencon.jobportal.model.request.VerifyOtpRequest;
import com.lawencon.jobportal.model.response.ListUserResponse;
import com.lawencon.jobportal.model.response.UserResponse;
import com.lawencon.jobportal.persistence.entity.User;
import com.lawencon.jobportal.persistence.entity.UserProfile;

public interface UserService
        extends CrudService<User, RegisterUserRequest, UpdateUserRequest, UserResponse> {
    Optional<User> login(LoginRequest request);

    Page<ListUserResponse> getAll(PagingRequest pagingRequest, String inquiry);

    UserDetailsService userDetailsService();

    UserProfile getUserProfile();

    void verifyUser(VerifyOtpRequest request);

}
