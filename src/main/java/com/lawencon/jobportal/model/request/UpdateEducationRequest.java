package com.lawencon.jobportal.model.request;

import java.math.BigDecimal;
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
public class UpdateEducationRequest {
  @NotNullParam(fieldName = "Id")
  private String id;

  @NotBlankParam(fieldName = "School Name")
  private String schoolName;

  @NotBlankParam(fieldName = "Major")
  private String major;

  @NotNullParam(fieldName = "Start Date")
  private LocalDate startDate;

  @NotNullParam(fieldName = "End Date")
  private LocalDate endDate;

  @NotNullParam(fieldName = "Grade")
  private BigDecimal grade;

  @NotNullParam(fieldName = "Version")
  private Long version;
}

