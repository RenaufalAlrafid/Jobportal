package com.lawencon.jobportal.model.request;

import com.lawencon.jobportal.persistence.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOtpRequest {
  private User user;
  private String otp;
}
