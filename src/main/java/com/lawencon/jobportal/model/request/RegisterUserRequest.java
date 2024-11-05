package com.lawencon.jobportal.model.request;

import com.lawencon.jobportal.validation.annotation.NotBlankParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequest {
    @NotBlankParam(fieldName = "username")
    private String username;

    @NotBlankParam(fieldName = "password")
    private String password;

    private String roleId;

    @NotBlankParam(fieldName = "gender")
    private String genderId;

    @NotBlankParam(fieldName = "email")
    private String email;

    @NotBlankParam(fieldName = "nik")
    private String nik;

    @NotBlankParam(fieldName = "full name")
    private String fullName;

    @NotBlankParam(fieldName = "birth date")
    private String birthDate;
}
