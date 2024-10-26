package com.lawencon.jobportal.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawencon.jobportal.persistence.entity.Experience;


public interface ExperienceRepository extends JpaRepository<Experience, String> {

  Optional<Experience> findByCode(String code);

}
