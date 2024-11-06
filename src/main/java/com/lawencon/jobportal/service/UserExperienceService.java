package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.request.CreateUserExperienceRequest;
import com.lawencon.jobportal.model.request.UpdateUserExperienceRequest;
import com.lawencon.jobportal.model.response.UserExperienceResponse;
import com.lawencon.jobportal.persistence.entity.UserExperience;

public interface UserExperienceService {

  public UserExperience getEntityById(String id);

  public UserExperienceResponse getById(String id);

  public void create(CreateUserExperienceRequest request);

  public void update(UpdateUserExperienceRequest request);

  public void delete(String id);

  public List<UserExperienceResponse> getAll();

}
