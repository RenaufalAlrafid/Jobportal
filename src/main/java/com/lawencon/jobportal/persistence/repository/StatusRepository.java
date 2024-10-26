package com.lawencon.jobportal.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lawencon.jobportal.persistence.entity.Status;

public interface StatusRepository extends JpaRepository<Status, String> {
  Optional<Status> findByCode(String code);
}
