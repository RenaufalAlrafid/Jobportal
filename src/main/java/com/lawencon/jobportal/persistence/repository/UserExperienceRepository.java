package com.lawencon.jobportal.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.lawencon.jobportal.persistence.entity.UserExperience;

public interface UserExperienceRepository
    extends JpaRepository<UserExperience, String>, JpaSpecificationExecutor<UserExperience> {
  List<UserExperience> findAllByProfileId(String profileId);
}
