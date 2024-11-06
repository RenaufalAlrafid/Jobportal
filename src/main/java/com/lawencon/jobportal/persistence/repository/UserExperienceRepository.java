package com.lawencon.jobportal.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lawencon.jobportal.persistence.entity.UserExperience;

public interface UserExperienceRepository extends JpaRepository<UserExperience, String> {
  List<UserExperience> findAllByProfileId(String profileId);
}
