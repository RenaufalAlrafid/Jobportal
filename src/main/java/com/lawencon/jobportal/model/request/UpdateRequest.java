package com.lawencon.jobportal.model.request;

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
public class UpdateRequest {
  @NotBlankParam(fieldName = "Id")
  private String id;

  @NotNullParam(fieldName = "version")
  private Long version;

  @NotNullParam(fieldName = "isActive")
  private Boolean isActive;

}
