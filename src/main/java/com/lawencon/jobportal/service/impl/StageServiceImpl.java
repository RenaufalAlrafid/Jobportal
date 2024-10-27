package com.lawencon.jobportal.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.persistence.entity.Stage;
import com.lawencon.jobportal.persistence.repository.StageRepository;
import com.lawencon.jobportal.service.StageService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StageServiceImpl implements StageService {
    StageRepository repository;

    @Override
    public List<ConstantResponse> getAll() {
        List<Stage> stages = repository.findAll();
        List<ConstantResponse> responses =
                stages.stream().map(stage -> new ConstantResponse(stage.getId(), stage.getCode(),
                        stage.getName(), stage.getVersion(), stage.getIsActive())).toList();
        return responses;
    }

    @Override
    public Stage getByCode(String code) {
        Optional<Stage> stage = repository.findByCode(code);
        if (!stage.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stage not found");
        }
        return stage.get();
    }

    @Override
    public Stage getEntityById(String id) {
        ValidationUtil.idIsExist(id, repository, "Stage");
        return repository.findById(id).get();
    }

}
