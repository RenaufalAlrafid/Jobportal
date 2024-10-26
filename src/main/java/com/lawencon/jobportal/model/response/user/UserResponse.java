package com.lawencon.jobportal.model.response.user;

import com.lawencon.jobportal.model.response.DetailResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends DetailResponse {
  private String username;
  private String roleId;
}
