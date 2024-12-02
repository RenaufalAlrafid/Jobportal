package com.lawencon.jobportal.model.response;

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
  private String username;
  private String role;
  private String genderId;
  private String gender;
  private String email;
  private String nik;
  private String fullName;
  private String phoneNumber;
  private String birthDate;
  private String address;
  private String city;
  private String photoUrl;
  private Boolean isActive;
  private Long version;
}
