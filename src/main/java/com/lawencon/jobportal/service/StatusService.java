package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.persistence.entity.Status;

public interface StatusService {
    List<ConstantResponse> getAll();

    List<ConstantResponse> getStatusVacancy();

    List<ConstantResponse> getStatusStage();

    Status getByCode(String code);

    Status getEntityById(String id);

}
