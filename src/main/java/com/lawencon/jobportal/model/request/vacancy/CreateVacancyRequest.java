package com.lawencon.jobportal.model.request.vacancy;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVacancyRequest {
  private String jobId;
  private String typeId;
  private String locationId;
  private String levelId;
  private Integer salaryStart;
  private Integer salaryEnd;
  private LocalDate dueDate;
  private String overview;
}
