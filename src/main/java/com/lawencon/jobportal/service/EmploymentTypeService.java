package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.persistence.entity.EmploymentType;

public interface EmploymentTypeService {
    List<ConstantResponse> getAll();

    EmploymentType getByCode(String code);

    EmploymentType getEntityById(String id);

}
