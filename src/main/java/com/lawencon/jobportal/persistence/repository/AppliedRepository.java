package com.lawencon.jobportal.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lawencon.jobportal.persistence.entity.Applied;

@Repository
public interface AppliedRepository extends JpaRepository<Applied, String> {

  Optional<Applied> findByUserId(String userId);
}
