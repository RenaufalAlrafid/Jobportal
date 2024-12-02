package com.lawencon.jobportal.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.lawencon.jobportal.model.request.CreateDescriptionRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.UpdateDescriptionRequest;
import com.lawencon.jobportal.model.response.DescriptionResponse;

public interface DescriptionService {
  List<DescriptionResponse> getAllByJobId(String jobId);

  Page<DescriptionResponse> findAllByJobId(String jobId, PagingRequest pagingRequest);

  void create(CreateDescriptionRequest request);

  void update(UpdateDescriptionRequest request);

  void delete(String id);
}
