package com.lawencon.jobportal.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lawencon.jobportal.persistence.entity.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

  Optional<UserProfile> findByUserId(String userId);

  Optional<UserProfile> findByEmail(String email);

  Boolean existsByEmail(String email);

  Boolean existsByNik(String nik);
}
