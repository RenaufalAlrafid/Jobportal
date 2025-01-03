package com.lawencon.jobportal.model.request;

import java.time.LocalDate;
import com.lawencon.jobportal.validation.annotation.NotBlankParam;
import com.lawencon.jobportal.validation.annotation.NotNullParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVacancyRequest {
  @NotBlankParam(fieldName = "Id")
  private String id;
  @NotBlankParam(fieldName = "Job Id")
  private String jobId;
  @NotBlankParam(fieldName = "Type Id")
  private String typeId;
  @NotBlankParam(fieldName = "Location Id")
  private String locationId;
  @NotBlankParam(fieldName = "Level Id")
  private String levelId;
  @NotNullParam(fieldName = "Salary Start")
  private Integer salaryStart;
  @NotNullParam(fieldName = "Salary End")
  private Integer salaryEnd;
  @NotNullParam(fieldName = "Due Date")
  private LocalDate dueDate;
  @NotBlankParam(fieldName = "Overview")
  private String overview;
  @NotNullParam(fieldName = "version")
  private Long version;
  @NotNullParam(fieldName = "isActive")
  private Boolean isActive;
}
