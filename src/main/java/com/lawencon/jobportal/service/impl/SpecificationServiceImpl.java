package com.lawencon.jobportal.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.CreateSpecificationRequest;
import com.lawencon.jobportal.model.request.UpdateSpecificationRequest;
import com.lawencon.jobportal.model.response.SpecificationResponse;
import com.lawencon.jobportal.persistence.entity.Specification;
import com.lawencon.jobportal.persistence.repository.SpecificationRepository;
import com.lawencon.jobportal.service.SpecificationService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SpecificationServiceImpl implements SpecificationService {
  private final SpecificationRepository repository;

  @Override
  public List<SpecificationResponse> getAllByJobId(String jobId) {
    List<Specification> specification = repository.findAllByJobId(jobId);
    List<SpecificationResponse> responses = specification.stream().map(spec -> {
      return new SpecificationResponse(spec.getId(), spec.getSpecification());
    }).toList();
    return responses;
  }

  @Override
  public void create(CreateSpecificationRequest request) {
    Specification specification = new Specification();
    specification.setSpecification(request.getSpecification());
    specification.setJob(request.getJob());
    specification.setVersion(0L);
    repository.save(specification);
  }

  @Override
  public void update(UpdateSpecificationRequest request) {
    ValidationUtil.idIsExist(request.getId(), repository, "Specification");
    Specification specification = repository.findById(request.getId()).get();
    specification.setSpecification(request.getSpecification());
    specification.setVersion(specification.getVersion() + 1);
    repository.saveAndFlush(specification);
  }

  @Override
  public void delete(String id) {
    ValidationUtil.idIsExist(id, repository, "Specification");
    Specification specification = repository.findById(id).get();
    specification.setVersion(specification.getVersion() + 1);
    repository.delete(specification);
  }


}
