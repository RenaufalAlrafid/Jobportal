package com.lawencon.jobportal.model.request.assign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignReportRequest {
  private String id;
  private String code;
  private String title;
  private String type;
  private String loc;
  private String level;
  private String salary;
  private String status;
}
