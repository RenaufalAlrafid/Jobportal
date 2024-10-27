package com.lawencon.jobportal.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lawencon.jobportal.persistence.entity.VacancyTrx;

public interface VacancyTrxRepository extends JpaRepository<VacancyTrx, String> {
  Optional<VacancyTrx> findFirstByAssignIdOrderByCreatedAtDesc(String assignId);
}
