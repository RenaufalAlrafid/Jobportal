package com.lawencon.jobportal.helper;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.jpa.repository.JpaRepository;

public class ValidationUtil {

  // Validate if the ID is not null
  public static void idNotNull(String id, String entityName) {
    if (id == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, entityName + " Id is required");
    }
  }

  // Validate if the ID exists in the repository
  public static <T> void idIsExist(String id, JpaRepository<T, String> repository,
      String entityName) {
    boolean isExist = repository.existsById(id);
    if (!isExist) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, entityName + " Id does not exist");
    }
  }

  // Validate optional entity if present or throw exception
  public static <T> T validateEntityExists(Optional<T> entity, String entityName) {
    return entity.orElseThrow(
        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, entityName + " not found"));
  }
}
