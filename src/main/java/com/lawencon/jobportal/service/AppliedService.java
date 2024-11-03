package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.request.CreateAppliedRequest;
import com.lawencon.jobportal.model.request.CreateStageRequest;
import com.lawencon.jobportal.model.request.UpdateStageRequest;
import com.lawencon.jobportal.model.response.AppliedResponse;
import com.lawencon.jobportal.model.response.ListAppliedResponse;

public interface AppliedService {
  void create(CreateAppliedRequest data);

  List<ListAppliedResponse> getAllByAssignId(String appliedId);

  AppliedResponse getAppliedById(String appliedId);

  void updateStage(UpdateStageRequest data);

  void createStage(CreateStageRequest data);
}
