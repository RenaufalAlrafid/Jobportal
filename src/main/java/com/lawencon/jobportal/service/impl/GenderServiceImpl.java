package com.lawencon.jobportal.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.persistence.entity.Gender;
import com.lawencon.jobportal.persistence.repository.GenderRepository;
import com.lawencon.jobportal.service.GenderService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GenderServiceImpl implements GenderService {
    private final GenderRepository repository;

    @Override
    public List<ConstantResponse> getAll() {
        List<Gender> genders = repository.findAll();
        List<ConstantResponse> responses =
                genders.stream()
                        .map(gender -> new ConstantResponse(gender.getId(), gender.getCode(),
                                gender.getName(), gender.getVersion(), gender.getIsActive()))
                        .toList();
        return responses;
    }

    @Override
    public Gender getByCode(String code) {
        Optional<Gender> gender = repository.findByCode(code);
        if (!gender.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gender not found");
        }
        return gender.get();
    }

    @Override
    public Gender getEntityById(String id) {
        ValidationUtil.idIsExist(id, repository, "Gender");
        return repository.findById(id).get();
    }

}
