package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.persistence.entity.Gender;

public interface GenderService {
  List<ConstantResponse> getAll();

  Gender getByCode(String code);

  Gender getEntityById(String id);
}
