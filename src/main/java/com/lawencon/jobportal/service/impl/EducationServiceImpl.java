
package com.lawencon.jobportal.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.lawencon.jobportal.helper.MappingUtil;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.CreateEducationRequest;
import com.lawencon.jobportal.model.request.UpdateEducationRequest;
import com.lawencon.jobportal.model.response.EducationResponse;
import com.lawencon.jobportal.persistence.entity.Education;
import com.lawencon.jobportal.persistence.entity.UserProfile;
import com.lawencon.jobportal.persistence.repository.EducationRepository;
import com.lawencon.jobportal.service.EducationService;
import com.lawencon.jobportal.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EducationServiceImpl implements EducationService {
  private final EducationRepository repository;
  private final UserService userService;

  @Override
  public Education getEntityById(String id) {
    ValidationUtil.idIsExist(id, repository, "Education");
    return repository.findById(id).get();
  }

  @Override
  public EducationResponse getById(String id) {
    Education education = getEntityById(id);
    EducationResponse response = new EducationResponse();
    MappingUtil.map(education, response);
    response.setProfileId(education.getProfile().getId());
    return response;
  }

  @Override
  public void create(CreateEducationRequest request) {
    Education education = new Education();
    MappingUtil.map(request, education);
    education.setProfile(userService.getUserProfile());
    education.setVersion(0L);
    repository.saveAndFlush(education);
  }

  @Override
  public void update(UpdateEducationRequest request) {
    Education education = getEntityById(request.getId());
    MappingUtil.map(request, education);
    education.setVersion(education.getVersion() + 1);
    repository.saveAndFlush(education);
  }

  @Override
  public void delete(String id) {
    Education education = getEntityById(id);
    education.setVersion(education.getVersion() + 1);
    repository.delete(education);
  }

  @Override
  public List<EducationResponse> getAll() {
    UserProfile profile = userService.getUserProfile();
    List<Education> educations = repository.findAllByProfileId(profile.getId());
    List<EducationResponse> responses = educations.stream().map(education -> {
      EducationResponse response = new EducationResponse();
      MappingUtil.map(education, response);
      response.setProfileId(education.getProfile().getId());
      return response;
    }).toList();
    return responses;
  }
}
