package com.lawencon.jobportal.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.lawencon.jobportal.persistence.entity.Location;

public interface LocationRepository
    extends JpaRepository<Location, String>, JpaSpecificationExecutor<Location> {
  Optional<Location> findByCode(String code);

}
