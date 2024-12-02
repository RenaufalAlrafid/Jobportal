package com.lawencon.jobportal.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.lawencon.jobportal.persistence.entity.Education;

public interface EducationRepository
    extends JpaRepository<Education, String>, JpaSpecificationExecutor<Education> {
  List<Education> findAllByProfileId(String profileId);

}
