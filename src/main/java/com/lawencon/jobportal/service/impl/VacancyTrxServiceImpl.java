package com.lawencon.jobportal.service.impl;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.persistence.entity.VacancyTrx;
import com.lawencon.jobportal.persistence.repository.VacancyTrxRepository;
import com.lawencon.jobportal.service.VacancyTrxService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VacancyTrxServiceImpl implements VacancyTrxService {
  private final VacancyTrxRepository repository;

  @Override
  public void create(VacancyTrx data) {
    repository.save(data);
  }

  @Override
  public VacancyTrx getLastTrx(String id) {
    Optional<VacancyTrx> trx = repository.findFirstByAssignIdOrderByCreatedAtDesc(id);
    if (!trx.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vacancy Transaction not found");
    } else {
      return trx.get();
    }
  }



}
