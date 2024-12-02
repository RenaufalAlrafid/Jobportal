package com.lawencon.jobportal.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lawencon.jobportal.persistence.entity.AssignDetail;

@Repository
public interface AssignDetailRepository extends JpaRepository<AssignDetail, String> {

  Optional<AssignDetail> findByPicId(String id);

}
