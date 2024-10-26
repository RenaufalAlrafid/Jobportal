package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.persistence.entity.Role;

public interface RoleService {
    List<ConstantResponse> getAll();

    Role getByCode(String code);

    Role getEntityById(String id);

}
