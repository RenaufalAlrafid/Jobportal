package com.lawencon.jobportal.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.persistence.entity.StageTrx;
import com.lawencon.jobportal.persistence.repository.StageTrxRepository;
import com.lawencon.jobportal.service.StageTrxService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StageTrxServiceImpl implements StageTrxService {
  private StageTrxRepository repository;

  @Override
  public void create(StageTrx data) {
    repository.save(data);
  }

  @Override
  public StageTrx getEntityById(String id) {
    ValidationUtil.idIsExist(id, repository, "StageTrx");
    return repository.findById(id).get();
  }

  @Override
  public List<StageTrx> getAllByAssignId(String assignId) {
    return repository.findAllByAppliedIdOrderByCreatedAtAsc(assignId);
  }

  @Override
  public void update(StageTrx data) {
    repository.saveAndFlush(data);
  }
}
