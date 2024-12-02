package com.lawencon.jobportal.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.lawencon.jobportal.persistence.entity.Specification;

@Repository
public interface SpecificationRepository
    extends JpaRepository<Specification, String>, JpaSpecificationExecutor<Specification> {
  List<Specification> findAllByJobId(String jobId);

}
