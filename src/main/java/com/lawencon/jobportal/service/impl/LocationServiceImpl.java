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
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.persistence.entity.Location;
import com.lawencon.jobportal.persistence.repository.LocationRepository;
import com.lawencon.jobportal.service.LocationService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
  private final LocationRepository repository;

  private void validationCode(String code) {
    Optional<Location> location = repository.findByCode(code);
    if (location.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location code already exist");
    }
  }

  @Override
  public Location getEntityById(String id) {
    Optional<Location> location = repository.findById(id);
    if (!location.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location not found");
    }
    return location.get();
  }

  @Override
  public ConstantResponse getByid(String id) {
    Location location = getEntityById(id);
    ConstantResponse response = new ConstantResponse();
    response.setId(id);
    response.setCode(location.getCode());
    response.setName(location.getName());
    response.setVersion(location.getVersion());
    response.setIsActive(location.getIsActive());
    return response;
  }

  @Override
  public void create(CreateMasterRequest request) {
    validationCode(request.getCode());
    Location location = new Location();
    location.setCode(request.getCode());
    location.setName(request.getName());
    location.setIsActive(true);
    location.setVersion(0L);
    repository.save(location);
  }

  @Override
  public void update(UpdateMasterRequest request) {
    Location location = getEntityById(request.getId());
    if (!location.getCode().equals(request.getCode())) {
      validationCode(request.getCode());
    }
    location.setCode(request.getCode());
    location.setName(request.getName());
    location.setIsActive(request.getIsActive());
    location.setVersion(location.getVersion() + 1L);
    repository.save(location);
  }

  @Override
  public void delete(String id) {
    Location location = getEntityById(id);
    location.setIsActive(false);
    location.setVersion(location.getVersion() + 1L);
    repository.delete(location);
  }

  @Override
  public Page<ConstantResponse> getAll(PagingRequest pagingRequest, String inquiry) {
    PageRequest pageRequest = PageRequest.of(pagingRequest.getPage(), pagingRequest.getPageSize(),
        SpecificationHelper.createSort(pagingRequest.getSortBy()));

    Specification<Location> spec = Specification.where(null);
    if (inquiry != null && !inquiry.isBlank()) {
      spec = spec.and(SpecificationHelper.inquiryFilter(Arrays.asList("name"), inquiry));
    }

    Page<Location> locationResponse = repository.findAll(spec, pageRequest);
    List<ConstantResponse> response = locationResponse.map(entity -> {
      ConstantResponse res = new ConstantResponse();
      res.setId(entity.getId());
      res.setCode(entity.getCode());
      res.setName(entity.getName());
      res.setVersion(entity.getVersion());
      res.setIsActive(entity.getIsActive());
      return res;
    }).toList();
    return new PageImpl<>(response, pageRequest, locationResponse.getTotalElements());
  }


}
