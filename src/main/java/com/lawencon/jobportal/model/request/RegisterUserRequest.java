package com.lawencon.jobportal.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequest {
    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    private String roleId;

    @NotNull(message = "Gender ID cannot be null")
    @NotBlank(message = "Gender ID cannot be empty")
    private String genderId;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "NIK cannot be null")
    @NotBlank(message = "NIK cannot be empty")
    private String nik;

    @NotNull(message = "Full name cannot be null")
    @NotBlank(message = "Full name cannot be empty")
    private String fullName;

    @NotNull(message = "Birth date cannot be null")
    @NotBlank(message = "Birth date cannot be empty")
    private String birthDate;
}
