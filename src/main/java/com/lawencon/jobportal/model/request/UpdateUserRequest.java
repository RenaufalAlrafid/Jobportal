package com.lawencon.jobportal.model.request;

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
public class UpdateUserRequest {
  @NotBlankParam(fieldName = "username")
  private String username;

  @NotBlankParam(fieldName = "password")
  private String roleId;

  @NotBlankParam(fieldName = "id")
  private String id;

  @NotNullParam(fieldName = "version")
  private Long version;

  @NotNullParam(fieldName = "isActive")
  private Boolean isActive;


}
