package com.lawencon.jobportal.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lawencon.jobportal.persistence.entity.Description;
@Repository
public interface DescriptionRepository extends JpaRepository<Description, String> {
  List<Description> findAllByJobId(String jobId);
}
