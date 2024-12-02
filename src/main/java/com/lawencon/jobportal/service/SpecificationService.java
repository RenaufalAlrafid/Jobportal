package com.lawencon.jobportal.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.lawencon.jobportal.model.request.CreateSpecificationRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.UpdateSpecificationRequest;
import com.lawencon.jobportal.model.response.SpecificationResponse;

public interface SpecificationService {
  List<SpecificationResponse> getAllByJobId(String jobId);

  Page<SpecificationResponse> findAllByJobId(String jobId, PagingRequest pagingRequest);

  void create(CreateSpecificationRequest request);

  void update(UpdateSpecificationRequest request);

  void delete(String id);
}
