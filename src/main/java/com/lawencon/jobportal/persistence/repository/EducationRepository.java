package com.lawencon.jobportal.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lawencon.jobportal.persistence.entity.Education;

public interface EducationRepository extends JpaRepository<Education, String> {
  List<Education> findAllByProfileId(String profileId);

}
