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
public class UpdateUserProfileRequest {

  @NotBlankParam(fieldName = "gender id")
  private String genderId;

  @NotBlankParam(fieldName = "email")
  private String email;

  @NotBlankParam(fieldName = "nik")
  private String nik;

  @NotBlankParam(fieldName = "full name")
  private String fullName;

  @NotBlankParam(fieldName = "birth date")
  private String birthDate;

  private String address;
  private String phoneNumber;
  private String city;

  @NotNullParam(fieldName = "version")
  private Long version;

  @NotNullParam(fieldName = "is active")
  private Boolean isActive;
}
