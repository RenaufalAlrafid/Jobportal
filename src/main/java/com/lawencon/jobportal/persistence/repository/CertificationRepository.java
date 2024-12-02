package com.lawencon.jobportal.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.lawencon.jobportal.persistence.entity.Certification;

public interface CertificationRepository
    extends JpaRepository<Certification, String>, JpaSpecificationExecutor<Certification> {
  List<Certification> findAllByProfileId(String profileId);

}
