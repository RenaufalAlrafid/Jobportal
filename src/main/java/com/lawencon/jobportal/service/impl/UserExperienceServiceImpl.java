
package com.lawencon.jobportal.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.lawencon.jobportal.helper.MappingUtil;
import com.lawencon.jobportal.helper.SpecificationHelper;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.CreateUserExperienceRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
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
  public Page<UserExperienceResponse> getAll(PagingRequest pagingRequest, String inquiry) {
    PageRequest pageRequest = PageRequest.of(pagingRequest.getPage(), pagingRequest.getPageSize(),
        SpecificationHelper.createSort(pagingRequest.getSortBy()));

    Specification<UserExperience> spec = Specification.where(null);
    if (inquiry != null && !inquiry.isBlank()) {
      spec = spec.and(SpecificationHelper.inquiryFilter(Arrays.asList("name"), inquiry));
    }
    UserProfile profile = userService.getUserProfile();
    spec =
        spec.and(SpecificationHelper.inquiryFilter(Arrays.asList("profile.id"), profile.getId()));
    Page<UserExperience> userExperiences = repository.findAll(spec, pageRequest);
    List<UserExperienceResponse> responses = userExperiences.stream().map(userExperience -> {
      UserExperienceResponse response = new UserExperienceResponse();
      MappingUtil.map(userExperience, response);
      response.setProfileId(userExperience.getProfile().getId());
      return response;
    }).toList();
    return new PageImpl<>(responses, pageRequest, userExperiences.getTotalElements());
  }
}
