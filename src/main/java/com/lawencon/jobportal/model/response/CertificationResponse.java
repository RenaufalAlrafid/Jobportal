package com.lawencon.jobportal.model.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CertificationResponse {
  private String id;

  private String profileId;

  private String name;

  private String organization;

  private LocalDate date;

  private Long version;
}
