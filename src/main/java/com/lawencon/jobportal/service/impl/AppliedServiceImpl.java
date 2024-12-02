package com.lawencon.jobportal.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.authentication.helper.SessionHelper;
import com.lawencon.jobportal.helper.CodeUtil;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.CreateStageRequest;
import com.lawencon.jobportal.model.request.UpdateStageRequest;
import com.lawencon.jobportal.model.response.AppliedResponse;
import com.lawencon.jobportal.model.response.ListAppliedResponse;
import com.lawencon.jobportal.model.response.StageResponse;
import com.lawencon.jobportal.persistence.entity.Applied;
import com.lawencon.jobportal.persistence.entity.Assign;
import com.lawencon.jobportal.persistence.entity.Stage;
import com.lawencon.jobportal.persistence.entity.StageTrx;
import com.lawencon.jobportal.persistence.entity.Status;
import com.lawencon.jobportal.persistence.entity.User;
import com.lawencon.jobportal.persistence.repository.AppliedRepository;
import com.lawencon.jobportal.service.AppliedService;
import com.lawencon.jobportal.service.StageService;
import com.lawencon.jobportal.service.StageTrxService;
import com.lawencon.jobportal.service.StatusService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppliedServiceImpl implements AppliedService {
  private final AppliedRepository repository;
  // private final AssignService assignService;
  private final StageService stageService;
  private final StatusService statusService;
  private final StageTrxService trxService;

  @Override
  public void create(Assign assign) {
    User user = SessionHelper.getLoginUser();
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

  @Override
  public void validateUserDelete(String userId) {
    Optional<Applied> applied = repository.findByUserId(userId);
    if (applied.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "User cannot be deleted because data user is already use in Applied Database");
    }
  }
}
