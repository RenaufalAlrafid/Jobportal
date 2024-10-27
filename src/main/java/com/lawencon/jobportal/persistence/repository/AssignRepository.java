package com.lawencon.jobportal.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawencon.jobportal.persistence.entity.Assign;

public interface AssignRepository extends JpaRepository<Assign, String> {

}
