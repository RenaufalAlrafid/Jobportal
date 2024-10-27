package com.lawencon.jobportal.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.helper.SpecificationHelper;
import com.lawencon.jobportal.model.request.CreateMasterRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.UpdateMasterRequest;
import com.lawencon.jobportal.model.request.description.CreateDescriptionRequest;
import com.lawencon.jobportal.model.request.description.CreateJobDesc;
import com.lawencon.jobportal.model.request.specification.CreateJobSpec;
import com.lawencon.jobportal.model.request.specification.CreateSpecificationRequest;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.model.response.description.DescriptionResponse;
import com.lawencon.jobportal.model.response.job.JobResponse;
import com.lawencon.jobportal.model.response.specifocation.SpecificationResponse;
import com.lawencon.jobportal.persistence.entity.Job;
import com.lawencon.jobportal.persistence.repository.JobRepository;
import com.lawencon.jobportal.service.JobService;
import com.lawencon.jobportal.service.SpecificationService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {
  private final JobRepository repository;
  private final SpecificationService specificationService;
  private final DescriptionServiceImpl descriptionService;

  private void validationCode(String code) {
    Optional<Job> location = repository.findByCode(code);
    if (location.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Job code already exist");
    }
  }

  @Override
  public Job getEntityById(String id) {
    Optional<Job> job = repository.findById(id);
    if (!job.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Job not found");
    }
    return job.get();
  }

  @Override
  public JobResponse getByid(String id) {
    Job job = getEntityById(id);
    JobResponse response = new JobResponse();
    response.setId(id);
    response.setCode(job.getCode());
    response.setName(job.getName());
    response.setVersion(job.getVersion());
    response.setIsActive(job.getIsActive());
    List<SpecificationResponse> specificationResponses =
        specificationService.getAllByJobId(job.getId());
    response.setSpecifications(specificationResponses);
    List<DescriptionResponse> descriptionResponses = descriptionService.getAllByJobId(job.getId());
    response.setDescription(descriptionResponses);
    return response;
  }

  @Override
  public void create(CreateMasterRequest request) {
    validationCode(request.getCode());
    Job job = new Job();
    job.setCode(request.getCode());
    job.setName(request.getName());
    job.setIsActive(true);
    job.setVersion(0L);
    repository.save(job);
  }

  @Override
  public void update(UpdateMasterRequest request) {
    Job job = getEntityById(request.getId());
    if (!job.getCode().equals(request.getCode())) {
      validationCode(request.getCode());
    }
    job.setCode(request.getCode());
    job.setName(request.getName());
    job.setIsActive(request.getIsActive());
    job.setVersion(job.getVersion() + 1L);
    repository.save(job);
  }

  @Override
  public void delete(String id) {
    Job job = getEntityById(id);
    job.setIsActive(false);
    job.setVersion(job.getVersion() + 1L);
    repository.delete(job);
  }

  @Override
  public Page<ConstantResponse> getAll(PagingRequest pagingRequest, String inquiry) {
    PageRequest pageRequest = PageRequest.of(pagingRequest.getPage(), pagingRequest.getPageSize(),
        SpecificationHelper.createSort(pagingRequest.getSortBy()));

    Specification<Job> spec = Specification.where(null);
    if (inquiry != null && !inquiry.isBlank()) {
      spec = spec.and(SpecificationHelper.inquiryFilter(Arrays.asList("name"), inquiry));
    }

    Page<Job> jobResponse = repository.findAll(spec, pageRequest);
    List<ConstantResponse> response = jobResponse.map(entity -> {
      ConstantResponse res = new ConstantResponse();
      res.setId(entity.getId());
      res.setCode(entity.getCode());
      res.setName(entity.getName());
      res.setVersion(entity.getVersion());
      res.setIsActive(entity.getIsActive());
      return res;
    }).toList();

    return new PageImpl<>(response, pageRequest, jobResponse.getTotalElements());
  }

  @Override
  public void createSpec(CreateJobSpec request) {
    Job job = getEntityById(request.getId());
    CreateSpecificationRequest spec = new CreateSpecificationRequest();
    spec.setJob(job);
    spec.setSpecification(request.getSpecification());
    specificationService.create(spec);
  }

  @Override
  public void createDesc(CreateJobDesc request) {
    Job job = getEntityById(request.getId());
    CreateDescriptionRequest desc = new CreateDescriptionRequest();
    desc.setJob(job);
    desc.setDescription(request.getDescription());
    descriptionService.create(desc);
  }
}
