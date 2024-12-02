package com.lawencon.jobportal.service;

import org.springframework.data.domain.Page;
import com.lawencon.jobportal.model.request.CreateEducationRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.UpdateEducationRequest;
import com.lawencon.jobportal.model.response.EducationResponse;
import com.lawencon.jobportal.persistence.entity.Education;

public interface EducationService {

  public Education getEntityById(String id);

  public EducationResponse getById(String id);

  public void create(CreateEducationRequest request);

  public void update(UpdateEducationRequest request);

  public void delete(String id);

  public Page<EducationResponse> getAll(PagingRequest request, String inquiry);

}
