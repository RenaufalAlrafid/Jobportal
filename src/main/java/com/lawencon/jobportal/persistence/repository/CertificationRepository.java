package com.lawencon.jobportal.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lawencon.jobportal.persistence.entity.Certification;

public interface CertificationRepository extends JpaRepository<Certification, String> {
  List<Certification> findAllByProfileId(String profileId);

}
