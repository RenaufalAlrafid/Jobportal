package com.lawencon.jobportal.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.jobportal.authentication.service.AuthenticationService;
import com.lawencon.jobportal.helper.ResponseHelper;
import com.lawencon.jobportal.model.request.LoginRequest;
import com.lawencon.jobportal.model.request.RegisterUserRequest;
import com.lawencon.jobportal.model.request.VerifyOtpRequest;
import com.lawencon.jobportal.model.response.JwtAuthenticationResponse;
import com.lawencon.jobportal.model.response.UserVerifyResponse;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Auth", description = "Auth API endpoint")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<JwtAuthenticationResponse>> login(
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(ResponseHelper.ok(authenticationService.login(request)));
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<String>> createUser(
            @Valid @RequestBody RegisterUserRequest request) {
        userService.create(request);
        return ResponseEntity.ok(ResponseHelper.ok(
                "User has been created successfully Please Check your email for OTP code and verify your account"));
    }

    @PostMapping(value = "/users/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<String>> verify(
            @Valid @RequestBody VerifyOtpRequest request) {
        userService.verifyUser(request);
        return ResponseEntity
                .ok(ResponseHelper.ok(" OTP verification successful. Please proceed with login. "));
    }

    @GetMapping(value = "/users/email/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<UserVerifyResponse>> getEmail(@PathVariable String username) {
        return ResponseEntity.ok(ResponseHelper.ok(userService.getUserForVerify(username)));
    }


}
