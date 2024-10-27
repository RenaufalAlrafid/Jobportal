package com.lawencon.jobportal.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import com.lawencon.jobportal.authentication.helper.SessionHelper;
import com.lawencon.jobportal.helper.CodeUtil;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.applied.CreateAppliedRequest;
import com.lawencon.jobportal.model.request.applied.CreateStageRequest;
import com.lawencon.jobportal.model.request.applied.UpdateStageRequest;
import com.lawencon.jobportal.model.response.applied.AppliedResponse;
import com.lawencon.jobportal.model.response.applied.ListAppliedResponse;
import com.lawencon.jobportal.model.response.applied.StageResponse;
import com.lawencon.jobportal.persistence.entity.Applied;
import com.lawencon.jobportal.persistence.entity.Assign;
import com.lawencon.jobportal.persistence.entity.Stage;
import com.lawencon.jobportal.persistence.entity.StageTrx;
import com.lawencon.jobportal.persistence.entity.Status;
import com.lawencon.jobportal.persistence.entity.User;
import com.lawencon.jobportal.persistence.repository.AppliedRepository;
import com.lawencon.jobportal.service.AppliedService;
import com.lawencon.jobportal.service.AssignService;
import com.lawencon.jobportal.service.StageService;
import com.lawencon.jobportal.service.StageTrxService;
import com.lawencon.jobportal.service.StatusService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppliedServiceImpl implements AppliedService {
  private AppliedRepository repository;
  private AssignService assignService;
  private StageService stageService;
  private StatusService statusService;
  private StageTrxService trxService;

  @Override
  public void create(CreateAppliedRequest data) {
    User user = SessionHelper.getLoginUser();
    Assign assign = assignService.getEntityById(data.getAssignId());
    Applied applied = new Applied();
    applied.setUser(user);
    applied.setAssign(assign);
    applied.setVersion(0L);
    applied = repository.save(applied);

    StageTrx trx = new StageTrx();
    trx.setApplied(applied);
    Stage stage = stageService.getByCode("APL");
    trx.setStage(stage);
    Status status = statusService.getByCode("PEN");
    trx.setStatus(status);
    trx.setTrxDate(LocalDate.now());
    trx.setTrxNumber(CodeUtil.generateCode(4, "TRXS"));
    trxService.create(trx);
  }

  @Override
  public List<ListAppliedResponse> getAllByAssignId(String assignId) {
    List<Applied> applieds = repository.findAll();
    List<ListAppliedResponse> responses = applieds.stream().map(applied -> {
      return new ListAppliedResponse(applied.getId(),
          applied.getAssign().getVacancy().getJob().getName(), applied.getUser().getUsername());
    }).toList();
    return responses;
  }

  @Override
  public void updateStage(UpdateStageRequest data) {
    StageTrx trx = trxService.getEntityById(data.getId());
    trx.setScore(data.getScore());
    trx.setDate(LocalDate.parse(data.getDate()));
    trxService.update(trx);
  }

  @Override
  public void createStage(CreateStageRequest data) {
    ValidationUtil.idIsExist(data.getAppliedId(), repository, "applied");
    Applied applied = repository.findById(data.getAppliedId()).get();
    Stage stage = stageService.getEntityById(data.getStageId());
    Status status = statusService.getEntityById(data.getStatusId());
    StageTrx trx = new StageTrx();
    trx.setApplied(applied);
    trx.setStage(stage);
    trx.setStatus(status);
    trx.setTrxDate(LocalDate.now());
    trx.setTrxNumber(CodeUtil.generateCode(4, "TRXS"));
    trxService.create(trx);
  }

  @Override
  public AppliedResponse getAppliedById(String appliedId) {
    ValidationUtil.idIsExist(appliedId, repository, "Applied");
    Applied applied = repository.findById(appliedId).get();
    AppliedResponse response = new AppliedResponse();
    response.setId(applied.getId());
    response.setAssign(applied.getAssign().getVacancy().getJob().getName());
    response.setUser(applied.getUser().getUsername());
    List<StageTrx> trxs = trxService.getAllByAssignId(appliedId);
    List<StageResponse> stageResponses = trxs.stream().map(trx -> {
      StageResponse stageResponse = new StageResponse();
      stageResponse.setId(trx.getId());
      stageResponse.setStage(trx.getStage().getName());
      stageResponse.setScore(trx.getScore());
      stageResponse.setDate(trx.getDate() == null ? "" : trx.getDate().toString());
      return stageResponse;
    }).toList();
    response.setStages(stageResponses);
    return response;
  }
}
