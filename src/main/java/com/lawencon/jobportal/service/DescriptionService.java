package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.request.CreateDescriptionRequest;
import com.lawencon.jobportal.model.request.UpdateDescriptionRequest;
import com.lawencon.jobportal.model.response.DescriptionResponse;

public interface DescriptionService {
  List<DescriptionResponse> getAllByJobId(String jobId);

  void create(CreateDescriptionRequest request);

  void update(UpdateDescriptionRequest request);

  void delete(String id);
}
