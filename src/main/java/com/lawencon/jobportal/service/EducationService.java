package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.request.CreateEducationRequest;
import com.lawencon.jobportal.model.request.UpdateEducationRequest;
import com.lawencon.jobportal.model.response.EducationResponse;
import com.lawencon.jobportal.persistence.entity.Education;

public interface EducationService {

  public Education getEntityById(String id);

  public EducationResponse getById(String id);

  public void create(CreateEducationRequest request);

  public void update(UpdateEducationRequest request);

  public void delete(String id);

  public List<EducationResponse> getAll();

}
