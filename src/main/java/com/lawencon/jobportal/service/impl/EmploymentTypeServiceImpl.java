package com.lawencon.jobportal.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.persistence.entity.EmploymentType;
import com.lawencon.jobportal.persistence.repository.EmploymentTypeRepository;
import com.lawencon.jobportal.service.EmploymentTypeService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmploymentTypeServiceImpl implements EmploymentTypeService {
    EmploymentTypeRepository repository;

    @Override
    public List<ConstantResponse> getAll() {
        List<EmploymentType> employmentTypes = repository.findAll();
        List<ConstantResponse> responses = employmentTypes.stream()
                .map(employmentType -> new ConstantResponse(employmentType.getId(),
                        employmentType.getCode(), employmentType.getName(),
                        employmentType.getVersion(), employmentType.getIsActive()))
                .toList();
        return responses;
    }

    @Override
    public EmploymentType getByCode(String code) {
        Optional<EmploymentType> employmentType = repository.findByCode(code);
        if (!employmentType.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EmploymentType not found");
        }
        return employmentType.get();
    }

    @Override
    public EmploymentType getEntityById(String id) {
        ValidationUtil.idIsExist(id, repository, "EmploymentType");
        return repository.findById(id).get();
    }

}
