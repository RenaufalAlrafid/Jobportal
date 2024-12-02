package com.lawencon.jobportal.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.lawencon.jobportal.helper.SpecificationHelper;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.CreateDescriptionRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
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
    List<DescriptionResponse> responses = description.stream().map(data -> {
      DescriptionResponse response = new DescriptionResponse();
      response.setId(data.getId());
      response.setDescription(data.getDescription());
      response.setVersion(data.getVersion());
      return response;
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

  @Override
  public Page<DescriptionResponse> findAllByJobId(String jobId, PagingRequest pagingRequest) {
    PageRequest pageRequest = PageRequest.of(pagingRequest.getPage(), pagingRequest.getPageSize(),
        SpecificationHelper.createSort(pagingRequest.getSortBy()));

    Specification<Description> spec = Specification.where(null);
    spec = spec.and(SpecificationHelper.inquiryFilter(Arrays.asList("job.id"), jobId));
    Page<Description> jobResponse = repository.findAll(spec, pageRequest);
    List<DescriptionResponse> responses = jobResponse.map(data -> {
      DescriptionResponse response = new DescriptionResponse();
      response.setId(data.getId());
      response.setDescription(data.getDescription());
      response.setVersion(data.getVersion());
      return response;
    }).toList();

    return new PageImpl<>(responses, pageRequest, jobResponse.getTotalElements());
  }


}
