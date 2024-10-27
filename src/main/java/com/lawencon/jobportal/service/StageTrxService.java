package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.persistence.entity.StageTrx;

public interface StageTrxService {
  void create(StageTrx data);

  StageTrx getEntityById(String id);

  List<StageTrx> getAllByAssignId(String assignId);

  void update(StageTrx data);
}
