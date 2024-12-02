package com.lawencon.jobportal.service.impl;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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

  @Override
  public void validateUserDelete(String userId) {
    Optional<AssignDetail> assignDetail = repository.findByPicId(userId);
    if (assignDetail.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "User Cannot Be Deleted because data is already in use in AssignDetail");
    }
  }
}
