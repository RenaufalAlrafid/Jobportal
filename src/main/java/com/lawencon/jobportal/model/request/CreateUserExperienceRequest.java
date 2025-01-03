package com.lawencon.jobportal.model.request;

import java.time.LocalDate;
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
public class CreateUserExperienceRequest {
  @NotBlankParam(fieldName = "Company Name")
  private String companyName;

  @NotBlankParam(fieldName = "job_title")
  private String jobTitle;

  @NotNullParam(fieldName = "Start Date")
  private LocalDate startDate;

  private LocalDate endDate;

  private String responsibilities;

  private String location;

}

