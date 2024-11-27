package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.request.CreateCertificationRequest;
import com.lawencon.jobportal.model.request.UpdateCertificationRequest;
import com.lawencon.jobportal.model.response.CertificationResponse;
import com.lawencon.jobportal.persistence.entity.Certification;

public interface CertificationService {

  public Certification getEntityById(String id);

  public CertificationResponse getById(String id);

  public void create(CreateCertificationRequest request);

  public void update(UpdateCertificationRequest request);

  public void delete(String id);

  public List<CertificationResponse> getAll();

}