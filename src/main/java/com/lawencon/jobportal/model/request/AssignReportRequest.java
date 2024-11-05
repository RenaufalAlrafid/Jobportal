package com.lawencon.jobportal.model.request;

import com.lawencon.jobportal.validation.annotation.NotBlankParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignReportRequest {
  @NotBlankParam(fieldName = "id")
  private String id;
  @NotBlankParam(fieldName = "code")
  private String code;
  @NotBlankParam(fieldName = "title")
  private String title;
  @NotBlankParam(fieldName = "type")
  private String type;
  @NotBlankParam(fieldName = "Location")
  private String loc;
  @NotBlankParam(fieldName = "level")
  private String level;
  private String salary;
  private String status;
}
