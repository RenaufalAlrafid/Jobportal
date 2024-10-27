package com.lawencon.jobportal.model.request.applied;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStageRequest {
  private String id;
  private Integer score;
  private String date;
}
