
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
import com.lawencon.jobportal.model.request.CreateCertificationRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.UpdateCertificationRequest;
import com.lawencon.jobportal.model.response.CertificationResponse;
import com.lawencon.jobportal.persistence.entity.Certification;
import com.lawencon.jobportal.persistence.entity.UserProfile;
import com.lawencon.jobportal.persistence.repository.CertificationRepository;
import com.lawencon.jobportal.service.CertificationService;
import com.lawencon.jobportal.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CertificationServiceImpl implements CertificationService {
  private final CertificationRepository repository;
  private final UserService userService;

  @Override
  public Certification getEntityById(String id) {
    ValidationUtil.idIsExist(id, repository, "Certification");
    return repository.findById(id).get();
  }

  @Override
  public CertificationResponse getById(String id) {
    Certification certification = getEntityById(id);
    CertificationResponse response = new CertificationResponse();
    MappingUtil.map(certification, response);
    response.setProfileId(certification.getProfile().getId());
    return response;
  }

  @Override
  public void create(CreateCertificationRequest request) {
    Certification certification = new Certification();
    MappingUtil.map(request, certification);
    certification.setProfile(userService.getUserProfile());
    certification.setVersion(0L);
    repository.saveAndFlush(certification);
  }

  @Override
  public void update(UpdateCertificationRequest request) {
    Certification certification = getEntityById(request.getId());
    MappingUtil.map(request, certification);
    certification.setVersion(certification.getVersion() + 1);
    repository.saveAndFlush(certification);
  }

  @Override
  public void delete(String id) {
    Certification certification = getEntityById(id);
    certification.setVersion(certification.getVersion() + 1);
    repository.delete(certification);
  }

  @Override
  public Page<CertificationResponse> getAll(PagingRequest pagingRequest, String inquiry) {
    PageRequest pageRequest = PageRequest.of(pagingRequest.getPage(), pagingRequest.getPageSize(),
        SpecificationHelper.createSort(pagingRequest.getSortBy()));

    Specification<Certification> spec = Specification.where(null);
    if (inquiry != null && !inquiry.isBlank()) {
      spec = spec.and(SpecificationHelper.inquiryFilter(Arrays.asList("name"), inquiry));
    }
    UserProfile profile = userService.getUserProfile();
    spec =
        spec.and(SpecificationHelper.inquiryFilter(Arrays.asList("profile.id"), profile.getId()));

    Page<Certification> certifications = repository.findAll(spec, pageRequest);
    List<CertificationResponse> responses = certifications.stream().map(userExperience -> {
      CertificationResponse response = new CertificationResponse();
      MappingUtil.map(userExperience, response);
      response.setProfileId(userExperience.getProfile().getId());
      return response;
    }).toList();
    return new PageImpl<>(responses, pageRequest, certifications.getTotalElements());
  }


}
