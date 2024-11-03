package com.lawencon.jobportal.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.CreateDescriptionRequest;
import com.lawencon.jobportal.model.request.UpdateDescriptionRequest;
import com.lawencon.jobportal.model.response.DescriptionResponse;
import com.lawencon.jobportal.persistence.entity.Description;
import com.lawencon.jobportal.persistence.repository.DescriptionRepository;
import com.lawencon.jobportal.service.DescriptionService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DescriptionServiceImpl implements DescriptionService {
  private final DescriptionRepository repository;

  @Override
  public List<DescriptionResponse> getAllByJobId(String jobId) {
    List<Description> description = repository.findAllByJobId(jobId);
    List<DescriptionResponse> responses = description.stream().map(spec -> {
      return new DescriptionResponse(spec.getId(), spec.getDescription());
    }).toList();
    return responses;
  }

  @Override
  public void create(CreateDescriptionRequest request) {
    Description description = new Description();
    description.setDescription(request.getDescription());
    description.setJob(request.getJob());
    description.setVersion(0L);
    repository.save(description);
  }

  @Override
  public void update(UpdateDescriptionRequest request) {
    ValidationUtil.idIsExist(request.getId(), repository, "Description");
    Description description = repository.findById(request.getId()).get();
    description.setDescription(request.getDescription());
    description.setVersion(description.getVersion() + 1);
    repository.saveAndFlush(description);
  }

  @Override
  public void delete(String id) {
    ValidationUtil.idIsExist(id, repository, "Description");
    Description description = repository.findById(id).get();
    description.setVersion(description.getVersion() + 1);
    repository.delete(description);
  }


}
