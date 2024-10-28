package com.lawencon.jobportal.persistence.repository;

import com.lawencon.jobportal.persistence.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StageRepository extends JpaRepository<Stage, String> {
    Optional<Stage> findByCode(String code);
}
