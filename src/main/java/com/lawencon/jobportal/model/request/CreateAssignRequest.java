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
public class CreateAssignRequest {
  @NotBlankParam(fieldName = "Vacancy Id")
  private String vacancyId;

  @NotBlankParam(fieldName = "PIC Id")
  private String picId;
}
