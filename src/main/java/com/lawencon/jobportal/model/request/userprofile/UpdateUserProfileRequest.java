package com.lawencon.jobportal.model.request.userprofile;

import com.lawencon.jobportal.validation.annotation.NotBlankParam;
import com.lawencon.jobportal.validation.annotation.NotNullParam;
import jakarta.validation.constraints.NotNull;
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
  @NotNullParam(fieldName = "gender id")
  private String genderId;

  @NotBlankParam(fieldName = "email")
  @NotNullParam(fieldName = "email")
  private String email;

  @NotBlankParam(fieldName = "nik")
  @NotNullParam(fieldName = "nik")
  private String nik;

  @NotBlankParam(fieldName = "full name")
  @NotNullParam(fieldName = "full name")
  private String fullName;

  @NotBlankParam(fieldName = "birth date")
  @NotNullParam(fieldName = "birth date")
  private String birthDate;

  private String address;
  private String phoneNumber;
  private String city;

  @NotNull(message = "Version cannot be null")
  private Long version;

  @NotNull(message = "isActive cannot be null")
  private Boolean isActive;
}
