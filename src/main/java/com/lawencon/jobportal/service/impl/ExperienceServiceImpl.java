package com.lawencon.jobportal.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.persistence.entity.Experience;
import com.lawencon.jobportal.persistence.repository.ExperienceRepository;
import com.lawencon.jobportal.service.ExperienceService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository repository;

    @Override
    public List<ConstantResponse> getAll() {
        List<Experience> experiences = repository.findAll();
        List<ConstantResponse> responses = experiences.stream()
                .map(experience -> new ConstantResponse(experience.getId(), experience.getCode(),
                        experience.getName(), experience.getVersion(), experience.getIsActive()))
                .toList();
        return responses;
    }

    @Override
    public Experience getByCode(String code) {
        Optional<Experience> experience = repository.findByCode(code);
        if (!experience.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Experience not found");
        }
        return experience.get();
    }

    @Override
    public Experience getEntityById(String id) {
        ValidationUtil.idIsExist(id, repository, "Experience");
        return repository.findById(id).get();
    }
}
