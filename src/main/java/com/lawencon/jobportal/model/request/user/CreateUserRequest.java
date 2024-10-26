package com.lawencon.jobportal.model.request.user;

import com.lawencon.jobportal.validation.annotation.NotBlankParam;
import com.lawencon.jobportal.validation.annotation.NotNullParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotBlankParam(fieldName = "username", message = "Username cannot be empty")
    @NotNullParam(fieldName = "username", message = "Username cannot be null")
    private String username;

    @NotBlankParam(fieldName = "password", message = "Password cannot be empty")
    @NotNullParam(fieldName = "password", message = "Password cannot be null")
    private String password;


    @NotBlankParam(fieldName = "role", message = "Role cannot be empty")
    @NotNullParam(fieldName = "role", message = "Role cannot be null")
    private String roleId;
}
