package com.lawencon.jobportal.model.request.description;

import com.lawencon.jobportal.persistence.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDescriptionRequest {
  private Job job;
  private String description;
}
