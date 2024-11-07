
package com.lawencon.jobportal.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.lawencon.jobportal.helper.MappingUtil;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.CreateCertificationRequest;
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
  public List<CertificationResponse> getAll() {
    UserProfile profile = userService.getUserProfile();
    List<Certification> certifications = repository.findAllByProfileId(profile.getId());
    List<CertificationResponse> responses = certifications.stream().map(certification -> {
      CertificationResponse response = new CertificationResponse();
      MappingUtil.map(certification, response);
      response.setProfileId(certification.getProfile().getId());
      return response;
    }).toList();
    return responses;
  }
}
