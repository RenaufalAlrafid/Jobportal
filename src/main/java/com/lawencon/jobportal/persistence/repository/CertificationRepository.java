package com.lawencon.jobportal.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.lawencon.jobportal.persistence.entity.Certification;

@Repository
public interface CertificationRepository
    extends JpaRepository<Certification, String>, JpaSpecificationExecutor<Certification> {
  List<Certification> findAllByProfileId(String profileId);

}
