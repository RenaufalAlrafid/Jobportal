package com.lawencon.jobportal.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawencon.jobportal.persistence.entity.EmploymentType;
import java.util.Optional;


public interface EmploymentTypeRepository extends JpaRepository<EmploymentType, String> {
  Optional<EmploymentType> findByCode(String code);
}
