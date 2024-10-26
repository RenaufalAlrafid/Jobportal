package com.lawencon.jobportal.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.persistence.entity.Role;
import com.lawencon.jobportal.persistence.repository.RoleRepository;
import com.lawencon.jobportal.service.RoleService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    RoleRepository repository;

    @Override
    public List<ConstantResponse> getAll() {
        List<Role> roles = repository.findAll();
        List<ConstantResponse> responses = roles.stream()
                .map(role -> new ConstantResponse(role.getId(), role.getCode(), role.getName()))
                .toList();
        return responses;
    }

    @Override
    public Role getByCode(String code) {
        Optional<Role> role = repository.findByCode(code);
        if (!role.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found");
        }
        return role.get();
    }

    @Override
    public Role getEntityById(String id) {
        ValidationUtil.idIsExist(id, repository, "Role");
        return repository.findById(id).get();
    }

}
