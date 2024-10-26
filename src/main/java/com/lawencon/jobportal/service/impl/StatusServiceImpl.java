package com.lawencon.jobportal.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.persistence.entity.Status;
import com.lawencon.jobportal.persistence.repository.StatusRepository;
import com.lawencon.jobportal.service.StatusService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {
    StatusRepository repository;

    @Override
    public List<ConstantResponse> getAll() {
        List<Status> statuses = repository.findAll();
        List<ConstantResponse> responses = statuses.stream().map(
                status -> new ConstantResponse(status.getId(), status.getCode(), status.getName()))
                .toList();
        return responses;
    }

    @Override
    public Status getByCode(String code) {
        Optional<Status> status = repository.findByCode(code);
        if (!status.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status not found");
        }
        return status.get();
    }

    @Override
    public Status getEntityById(String id) {
        ValidationUtil.idIsExist(id, repository, "Status");
        return repository.findById(id).get();
    }

    @Override
    public List<ConstantResponse> getStatusVacancy() {
        String[] codes = {"PD", "OG", "CT"};
        List<ConstantResponse> responses = getAll().stream().filter(status -> {
            for (String code : codes) {
                if (code.equals(status.getCode())) {
                    return true;
                }
            }
            return false;
        }).toList();

        return responses;
    }

    @Override
    public List<ConstantResponse> getStatusStage() {
        String[] codes = {"LS", "TLS", "PEN"};
        List<ConstantResponse> responses = getAll().stream().filter(status -> {
            for (String code : codes) {
                if (code.equals(status.getCode())) {
                    return true;
                }
            }
            return false;
        }).toList();
        return responses;
    }

}
