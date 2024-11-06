
package com.lawencon.jobportal.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.lawencon.jobportal.helper.MappingUtil;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.CreateUserExperienceRequest;
import com.lawencon.jobportal.model.request.UpdateUserExperienceRequest;
import com.lawencon.jobportal.model.response.UserExperienceResponse;
import com.lawencon.jobportal.persistence.entity.UserExperience;
import com.lawencon.jobportal.persistence.entity.UserProfile;
import com.lawencon.jobportal.persistence.repository.UserExperienceRepository;
import com.lawencon.jobportal.service.UserExperienceService;
import com.lawencon.jobportal.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserExperienceServiceImpl implements UserExperienceService {
  private final UserExperienceRepository repository;
  private final UserService userService;

  @Override
  public UserExperience getEntityById(String id) {
    ValidationUtil.idIsExist(id, repository, "UserExperience");
    return repository.findById(id).get();
  }

  @Override
  public UserExperienceResponse getById(String id) {
    UserExperience userExperience = getEntityById(id);
    UserExperienceResponse response = new UserExperienceResponse();
    MappingUtil.map(userExperience, response);
    response.setProfileId(userExperience.getProfile().getId());
    return response;
  }

  @Override
  public void create(CreateUserExperienceRequest request) {
    UserExperience userExperience = new UserExperience();
    MappingUtil.map(request, userExperience);
    userExperience.setProfile(userService.getUserProfile());
    userExperience.setVersion(0L);
    repository.saveAndFlush(userExperience);
  }

  @Override
  public void update(UpdateUserExperienceRequest request) {
    UserExperience userExperience = getEntityById(request.getId());
    MappingUtil.map(request, userExperience);
    userExperience.setVersion(userExperience.getVersion() + 1);
    repository.saveAndFlush(userExperience);
  }

  @Override
  public void delete(String id) {
    UserExperience userExperience = getEntityById(id);
    userExperience.setVersion(userExperience.getVersion() + 1);
    repository.delete(userExperience);
  }

  @Override
  public List<UserExperienceResponse> getAll() {
    UserProfile profile = userService.getUserProfile();
    List<UserExperience> userExperiences = repository.findAllByProfileId(profile.getId());
    List<UserExperienceResponse> responses = userExperiences.stream().map(userExperience -> {
      UserExperienceResponse response = new UserExperienceResponse();
      MappingUtil.map(userExperience, response);
      response.setProfileId(userExperience.getProfile().getId());
      return response;
    }).toList();
    return responses;
  }
}
