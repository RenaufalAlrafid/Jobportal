package com.lawencon.jobportal.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.lawencon.jobportal.persistence.entity.UserExperience;

@Repository
public interface UserExperienceRepository
    extends JpaRepository<UserExperience, String>, JpaSpecificationExecutor<UserExperience> {
  List<UserExperience> findAllByProfileId(String profileId);
}
