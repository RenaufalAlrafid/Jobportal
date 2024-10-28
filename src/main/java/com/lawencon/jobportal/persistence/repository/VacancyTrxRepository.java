package com.lawencon.jobportal.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lawencon.jobportal.persistence.entity.VacancyTrx;

@Repository
public interface VacancyTrxRepository extends JpaRepository<VacancyTrx, String> {
  Optional<VacancyTrx> findFirstByAssignIdOrderByCreatedAtDesc(String assignId);
}
