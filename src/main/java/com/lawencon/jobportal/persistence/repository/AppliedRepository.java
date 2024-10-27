package com.lawencon.jobportal.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawencon.jobportal.persistence.entity.Applied;

public interface AppliedRepository extends JpaRepository<Applied, String> {
  
}
