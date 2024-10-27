package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.persistence.entity.Stage;

public interface StageService {
  List<ConstantResponse> getAll();

  Stage getByCode(String code);

  Stage getEntityById(String id);
}
