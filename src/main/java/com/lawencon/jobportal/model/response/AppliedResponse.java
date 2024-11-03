package com.lawencon.jobportal.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppliedResponse {
  private String id;
  private String assign;
  private String user;
  private List<StageResponse> stages;
}
