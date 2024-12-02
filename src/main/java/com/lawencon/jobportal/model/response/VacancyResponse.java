package com.lawencon.jobportal.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacancyResponse {
  private String id;
  private String job;
  private String jobId;
  private String location;
  private String locationId;
  private String type;
  private String typeId;
  private String level;
  private String levelId;
  private String code;
  private Integer salaryStart;
  private Integer salaryEnd;
  private String dueDate;
  private String overview;
  private Long version;
  private Boolean isActive;
}
