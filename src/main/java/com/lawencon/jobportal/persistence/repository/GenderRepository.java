package com.lawencon.jobportal.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lawencon.jobportal.persistence.entity.Gender;

public interface GenderRepository extends JpaRepository<Gender, String> {
  Optional<Gender> findByCode(String code);
}
