package com.lawencon.jobportal.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.lawencon.jobportal.persistence.entity.Vacancy;

@Repository
public interface VacancyRepository
    extends JpaRepository<Vacancy, String>, JpaSpecificationExecutor<Vacancy> {
  Optional<Vacancy> findByJobId(String jobId);
}
