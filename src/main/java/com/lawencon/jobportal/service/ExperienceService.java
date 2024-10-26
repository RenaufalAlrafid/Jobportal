package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.persistence.entity.Experience;

public interface ExperienceService {
    List<ConstantResponse> getAll();

    Experience getByCode(String code);

    Experience getEntityById(String id);

}
