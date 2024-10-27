package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.request.applied.CreateAppliedRequest;
import com.lawencon.jobportal.model.request.applied.CreateStageRequest;
import com.lawencon.jobportal.model.request.applied.UpdateStageRequest;
import com.lawencon.jobportal.model.response.applied.AppliedResponse;
import com.lawencon.jobportal.model.response.applied.ListAppliedResponse;

public interface AppliedService {
  void create(CreateAppliedRequest data);

  List<ListAppliedResponse> getAllByAssignId(String appliedId);

  AppliedResponse getAppliedById(String appliedId);

  void updateStage(UpdateStageRequest data);

  void createStage(CreateStageRequest data);
}
