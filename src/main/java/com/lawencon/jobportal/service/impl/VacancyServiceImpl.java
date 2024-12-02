package com.lawencon.jobportal.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.lawencon.jobportal.helper.CodeUtil;
import com.lawencon.jobportal.helper.MappingUtil;
import com.lawencon.jobportal.helper.SpecificationHelper;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.CreateVacancyRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.UpdateVacancyRequest;
import com.lawencon.jobportal.model.response.ListVacancyResponse;
import com.lawencon.jobportal.model.response.VacancyResponse;
import com.lawencon.jobportal.persistence.entity.EmploymentType;
import com.lawencon.jobportal.persistence.entity.Experience;
import com.lawencon.jobportal.persistence.entity.Job;
import com.lawencon.jobportal.persistence.entity.Location;
import com.lawencon.jobportal.persistence.entity.Vacancy;
import com.lawencon.jobportal.persistence.repository.VacancyRepository;
import com.lawencon.jobportal.service.EmploymentTypeService;
import com.lawencon.jobportal.service.ExperienceService;
import com.lawencon.jobportal.service.JobService;
import com.lawencon.jobportal.service.LocationService;
import com.lawencon.jobportal.service.VacancyService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VacancyServiceImpl implements VacancyService {
  private final VacancyRepository repository;

  private JobService jobService;
  private EmploymentTypeService typeService;
  private LocationService locationService;
  private ExperienceService experienceService;

  @Override
  public Vacancy getEntityById(String id) {
    ValidationUtil.idIsExist(id, repository, "Vacancy");
    return repository.findById(id).get();
  }

  @Override
  public VacancyResponse getByid(String id) {
    Vacancy vacancy = getEntityById(id);
    VacancyResponse response = new VacancyResponse();
    response.setJob(vacancy.getJob().getName());
    response.setJobId(vacancy.getJob().getId());
    response.setLocation(vacancy.getLocation().getName());
    response.setLocationId(vacancy.getLocation().getId());
    response.setType(vacancy.getType().getName());
    response.setTypeId(vacancy.getType().getId());
    response.setLevel(vacancy.getLevel().getName());
    response.setLevelId(vacancy.getLevel().getId());
    response.setDueDate(vacancy.getDueDate().toString());
    MappingUtil.map(vacancy, response);
    return response;
  }

  @Override
  public void create(CreateVacancyRequest request) {
    Job job = jobService.getEntityById(request.getJobId());
    EmploymentType type = typeService.getEntityById(request.getTypeId());
    Location location = locationService.getEntityById(request.getLocationId());
    Experience experience = experienceService.getEntityById(request.getLevelId());
    Vacancy vacancy = new Vacancy();
    MappingUtil.map(request, vacancy);
    vacancy.setJob(job);
    vacancy.setType(type);
    vacancy.setLocation(location);
    vacancy.setLevel(experience);
    vacancy.setIsActive(true);
    vacancy.setVersion(0L);
    vacancy.setCode(CodeUtil.generateCode(4, "VAC"));
    repository.save(vacancy);
  }

  @Override
  public void update(UpdateVacancyRequest request) {
    Vacancy vacancy = getEntityById(request.getId());
    EmploymentType type = typeService.getEntityById(request.getTypeId());
    Location location = locationService.getEntityById(request.getLocationId());
    Experience experience = experienceService.getEntityById(request.getLevelId());
    MappingUtil.map(request, vacancy);
    vacancy.setType(type);
    vacancy.setLocation(location);
    vacancy.setLevel(experience);
    vacancy.setIsActive(request.getIsActive());
    vacancy.setVersion(vacancy.getVersion() + 1);
    repository.saveAndFlush(vacancy);
  }

  @Override
  public void delete(String id) {
    Vacancy vacancy = getEntityById(id);
    vacancy.setIsActive(false);
    vacancy.setVersion(vacancy.getVersion() + 1);
    repository.delete(vacancy);
  }

  @Override
  public Page<ListVacancyResponse> getAll(PagingRequest pagingRequest, String inquiry) {
    PageRequest pageRequest = PageRequest.of(pagingRequest.getPage(), pagingRequest.getPageSize(),
        SpecificationHelper.createSort(pagingRequest.getSortBy()));

    Specification<Vacancy> spec = Specification.where(null);
    if (inquiry != null && !inquiry.isBlank()) {
      spec = spec.and(SpecificationHelper
          .inquiryFilter(Arrays.asList("overview", "job.name", "location.name"), inquiry));
    }
    Page<Vacancy> vacancyResponse = repository.findAll(spec, pageRequest);
    List<ListVacancyResponse> responses = vacancyResponse.map(vacancy -> {
      ListVacancyResponse response = new ListVacancyResponse();
      response.setId(vacancy.getId());
      response.setJob(vacancy.getJob().getName());
      response.setLocation(vacancy.getLocation().getName());
      response.setType(vacancy.getType().getName());
      response.setLevel(vacancy.getLevel().getName());
      response.setCode(vacancy.getCode());
      response.setIsActive(vacancy.getIsActive());
      return response;
    }).toList();
    return new PageImpl<>(responses, pageRequest, vacancyResponse.getTotalElements());
  }


}
