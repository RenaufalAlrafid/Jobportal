package com.lawencon.jobportal.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListVacancyResponse {
  private String id;
  private String job;
  private String location;
  private String type;
  private String level;
  private String code;
  private Boolean isActive;
}
