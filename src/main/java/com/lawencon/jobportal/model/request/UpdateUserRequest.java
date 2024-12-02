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
public class UpdateUserRequest extends UpdateRequest {
  @NotNull(message = "Username cannot be null")
  @NotBlank(message = "Username cannot be empty")
  private String username;

  @NotNull(message = "Role cannot be null")
  @NotBlank(message = "Role cannot be empty")
  private String roleId;

  private Boolean isActive;
  private Long version;


}
