package com.lawencon.jobportal.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListUserResponse {
  private String id;
  private String fullName;
  private String username;
  private String roleName;
  private String roleId;
  private String email;
  private Boolean isActive;
}
