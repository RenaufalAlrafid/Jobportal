package com.lawencon.jobportal.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lawencon.jobportal.persistence.entity.Experience;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, String> {

  Optional<Experience> findByCode(String code);

}
