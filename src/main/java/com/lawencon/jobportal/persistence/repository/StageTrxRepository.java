package com.lawencon.jobportal.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lawencon.jobportal.persistence.entity.StageTrx;

public interface StageTrxRepository extends JpaRepository<StageTrx, String> {
  List<StageTrx> findAllByAppliedIdOrderByCreatedAtAsc(String appliedId);
}
