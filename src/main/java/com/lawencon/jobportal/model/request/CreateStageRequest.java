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
public class CreateStageRequest {
  @NotBlankParam(fieldName = "Applied Id")
  private String appliedId;
  @NotBlankParam(fieldName = "Stage Id")
  private String stageId;
  @NotBlankParam(fieldName = "Status Id")
  private String statusId;
}
