package com.lawencon.jobportal.controller;

import com.lawencon.jobportal.authentication.service.AuthenticationService;
import com.lawencon.jobportal.helper.ResponseHelper;
import com.lawencon.jobportal.model.request.user.LoginRequest;
import com.lawencon.jobportal.model.request.user.RegisterUserRequest;
import com.lawencon.jobportal.model.response.JwtAuthenticationResponse;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "Auth API endpoint")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<WebResponse<JwtAuthenticationResponse>> login(
            @RequestBody LoginRequest request) {
        return ResponseEntity.ok(ResponseHelper.ok(authenticationService.login(request)));
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<String>> createUser(
            @RequestBody RegisterUserRequest request) {
        userService.registerUser(request);
        return ResponseEntity.ok(ResponseHelper.ok("User has been created successfully"));
    }
}
