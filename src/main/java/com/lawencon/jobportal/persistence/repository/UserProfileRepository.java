package com.lawencon.jobportal.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lawencon.jobportal.persistence.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

  Optional<UserProfile> findByUserId(String userId);
}
