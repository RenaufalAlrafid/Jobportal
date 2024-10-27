package com.lawencon.jobportal.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lawencon.jobportal.persistence.entity.Specification;

public interface SpecificationRepository extends JpaRepository<Specification, String> {
  List<Specification> findAllByJobId(String jobId);

}
