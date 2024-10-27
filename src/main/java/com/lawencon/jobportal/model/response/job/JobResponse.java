package com.lawencon.jobportal.model.response.job;

import java.util.List;
import com.lawencon.jobportal.model.response.description.DescriptionResponse;
import com.lawencon.jobportal.model.response.specifocation.SpecificationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {
  private String id;
  private String code;
  private String name;
  private Long version;
  private Boolean isActive;
  private List<SpecificationResponse> specifications;
  private List<DescriptionResponse> description;
}
