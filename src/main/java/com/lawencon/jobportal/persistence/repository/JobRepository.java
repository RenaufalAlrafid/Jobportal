package com.lawencon.jobportal.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.lawencon.jobportal.persistence.entity.Job;

public interface JobRepository extends JpaRepository<Job, String>, JpaSpecificationExecutor<Job> {
  Job findByCode(String code);

}
