package com.lawencon.jobportal.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationResponse {

  private String id;

  private String profileId;

  private String schoolName;

  private String major;

  private LocalDate startDate;

  private LocalDate endDate;

  private BigDecimal grade;

  private Long version;
}
