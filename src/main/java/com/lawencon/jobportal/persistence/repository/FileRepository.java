package com.lawencon.jobportal.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawencon.jobportal.persistence.entity.File;

public interface FileRepository extends JpaRepository<File, String> {

}
