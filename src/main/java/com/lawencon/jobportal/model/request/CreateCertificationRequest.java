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
public class CreateCertificationRequest {
  @NotBlankParam(fieldName = "name")
  private String name;

  @NotBlankParam(fieldName = "organization")
  private String organization;

  @NotNullParam(fieldName = "date")
  private LocalDate date;
}
