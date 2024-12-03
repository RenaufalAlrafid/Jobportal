package com.lawencon.jobportal.helper;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.jpa.repository.JpaRepository;

public class ValidationUtil {

  public static void idNotNull(String id, String entityName) {
    if (id == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, entityName + " Id is required");
    }
  }
  public static <T> void idIsExist(String id, JpaRepository<T, String> repository,
      String entityName) {
    boolean isExist = repository.existsById(id);
    if (!isExist) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, entityName + " Id does not exist");
    }
  }

  public static <T> T validateEntityExists(Optional<T> entity, String entityName) {
    return entity.orElseThrow(
        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, entityName + " not found"));
  }

  public static void ValidateVersion(Long versionEntity, Long versionRequest) {
    if (versionRequest == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Version is required");
    }
    if (!versionRequest.equals(versionEntity)) {
      throw new ResponseStatusException(HttpStatus.CONFLICT,
          "You cannot send this request!! please reload your data");
    }

  }
}
