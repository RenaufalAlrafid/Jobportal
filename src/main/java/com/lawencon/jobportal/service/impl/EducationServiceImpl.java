
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
import com.lawencon.jobportal.model.request.CreateEducationRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
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
  public Page<EducationResponse> getAll(PagingRequest pagingRequest, String inquiry) {
    PageRequest pageRequest = PageRequest.of(pagingRequest.getPage(), pagingRequest.getPageSize(),
        SpecificationHelper.createSort(pagingRequest.getSortBy()));

    Specification<Education> spec = Specification.where(null);
    if (inquiry != null && !inquiry.isBlank()) {
      spec = spec.and(SpecificationHelper.inquiryFilter(Arrays.asList("name"), inquiry));
    }
    UserProfile profile = userService.getUserProfile();
    spec =
        spec.and(SpecificationHelper.inquiryFilter(Arrays.asList("profile.id"), profile.getId()));

    Page<Education> eductions = repository.findAll(spec, pageRequest);
    List<EducationResponse> responses = eductions.stream().map(userExperience -> {
      EducationResponse response = new EducationResponse();
      MappingUtil.map(userExperience, response);
      response.setProfileId(userExperience.getProfile().getId());
      return response;
    }).toList();
    return new PageImpl<>(responses, pageRequest, eductions.getTotalElements());
  }

}
