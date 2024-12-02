package com.lawencon.jobportal.persistence.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.lawencon.jobportal.persistence.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, String>, JpaSpecificationExecutor<Job> {
  Optional<Job> findByCode(String code);

  List<Job> findAllByIsActive(Boolean isActive);

}
