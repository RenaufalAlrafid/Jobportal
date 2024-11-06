package com.lawencon.jobportal.model.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserExperienceResponse {

  private String id;

  private String profileId;

  private String companyName;

  private String jobTitle;

  private LocalDate startDate;

  private LocalDate endDate;

  private String responsibilities;

  private String location;

  private Long version;
}
