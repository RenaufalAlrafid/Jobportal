package com.lawencon.jobportal.persistence.repository;

import com.lawencon.jobportal.persistence.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StageRepository extends JpaRepository<Stage, String> {
    Optional<Stage> findByCode(String code);
}
