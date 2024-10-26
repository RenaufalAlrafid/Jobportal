package com.lawencon.jobportal.model.response.userprofile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
  private String id;
  private String gender;
  private String email;
  private String nik;
  private String fullName;
  private String phoneNumber;
  private String dateOfBirth;
  private String address;
  private String city;
  
}
