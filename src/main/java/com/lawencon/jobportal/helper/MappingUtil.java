package com.lawencon.jobportal.helper;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

public class MappingUtil {

  // Generic method to map a single entity to a response DTO
  public static <E, R> void map(E from, R to) {
    BeanUtils.copyProperties(from, to);
  }

  // Generic method to map a list of entities to a list of response DTOs
  public static <E, R> List<R> mapListToResponse(List<E> entities, List<R> responseList,
      Class<R> responseType) {
    for (E entity : entities) {
      try {
        R response = responseType.getDeclaredConstructor().newInstance();
        map(entity, response);
        responseList.add(response);
      } catch (Exception e) {
        throw new RuntimeException("Failed to instantiate response type", e);
      }
    }
    return responseList;
  }

  public static void validateVersion(Long requestVersion, Long entityVersion, String entityName) {
    if (requestVersion == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          entityName + " version is required");
    }

    if (!requestVersion.equals(entityVersion)) {
      throw new ResponseStatusException(HttpStatus.CONFLICT,
          " You cannot send this request!! please reload your data");
    }
  }

}
