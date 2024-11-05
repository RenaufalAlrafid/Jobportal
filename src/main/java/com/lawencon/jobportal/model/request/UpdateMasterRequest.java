package com.lawencon.jobportal.model.request;

import com.lawencon.jobportal.validation.annotation.NotBlankParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMasterRequest extends UpdateRequest {
  @NotBlankParam(fieldName = "name")
  private String name;

  @NotBlankParam(fieldName = "code")
  private String code;
}
