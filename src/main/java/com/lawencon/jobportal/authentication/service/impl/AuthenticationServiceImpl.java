package com.lawencon.jobportal.authentication.service.impl;

import com.lawencon.jobportal.authentication.service.AuthenticationService;
import com.lawencon.jobportal.authentication.service.JwtService;
import com.lawencon.jobportal.model.request.LoginRequest;
import com.lawencon.jobportal.model.response.JwtAuthenticationResponse;
import com.lawencon.jobportal.persistence.entity.User;
import com.lawencon.jobportal.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public JwtAuthenticationResponse login(LoginRequest loginRequest) {
        Optional<User> userOpt = userService.login(loginRequest);

        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username or password wrong");
        }

        User user = userOpt.get();
        if (!user.getIsActive()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "User is not active, please look to your email address to get otp and verify your email to next page");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails userPrinciple =
                userService.userDetailsService().loadUserByUsername(loginRequest.getUsername());
        String token = jwtService.generateToken(userPrinciple);
        return JwtAuthenticationResponse.builder().token(token).build();
    }
}
