package com.lawencon.jobportal.model.request.applied;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStageRequest {
  private String appliedId;
  private String stageId;
  private String statusId;
}
