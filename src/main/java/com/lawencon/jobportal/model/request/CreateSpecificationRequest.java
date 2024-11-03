package com.lawencon.jobportal.model.request;

import com.lawencon.jobportal.persistence.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSpecificationRequest {
  private Job job;
  private String specification;
}
