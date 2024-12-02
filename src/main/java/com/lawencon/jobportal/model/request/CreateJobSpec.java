package com.lawencon.jobportal.model.request;

import com.lawencon.jobportal.validation.annotation.NotBlankParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobSpec {
  @NotBlankParam(fieldName = "id")
  private String jobId;

  @NotBlankParam(fieldName = "specification")
  private String specification;
}
