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
public class UpdateSpecificationRequest {
  @NotBlankParam(fieldName = "id")
  private String id;
  @NotBlankParam(fieldName = "specification")
  private String specification;
}
