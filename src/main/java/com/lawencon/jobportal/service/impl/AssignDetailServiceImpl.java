package com.lawencon.jobportal.service.impl;

import org.springframework.stereotype.Service;
import com.lawencon.jobportal.persistence.entity.AssignDetail;
import com.lawencon.jobportal.persistence.repository.AssignDetailRepository;
import com.lawencon.jobportal.service.AssignDetailService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssignDetailServiceImpl implements AssignDetailService {
  private final AssignDetailRepository repository;

  @Override
  public void create(AssignDetail data) {
    repository.save(data);
  }
}
