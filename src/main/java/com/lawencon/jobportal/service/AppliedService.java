package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.request.CreateStageRequest;
import com.lawencon.jobportal.model.request.UpdateStageRequest;
import com.lawencon.jobportal.model.response.AppliedResponse;
import com.lawencon.jobportal.model.response.ListAppliedResponse;
import com.lawencon.jobportal.persistence.entity.Assign;

public interface AppliedService {
  void create(Assign assign);

  List<ListAppliedResponse> getAllByAssignId(String appliedId);

  AppliedResponse getAppliedById(String appliedId);

  void updateStage(UpdateStageRequest data);

  void createStage(CreateStageRequest data);

  void validateUserDelete(String userId);
}
