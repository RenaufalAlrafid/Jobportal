package com.lawencon.jobportal.model.response.vacancy;

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
  private String location;
  private String type;
  private String level;
  private String code;
  private String salaryStart;
  private String salaryEnd;
  private String dueDate;
  private String overview;
  private Long version;
  private Boolean isActive;
}
