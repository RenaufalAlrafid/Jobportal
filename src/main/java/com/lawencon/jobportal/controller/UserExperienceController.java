package com.lawencon.jobportal.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.jobportal.helper.ResponseHelper;
import com.lawencon.jobportal.model.request.CreateUserExperienceRequest;
import com.lawencon.jobportal.model.request.UpdateUserExperienceRequest;
import com.lawencon.jobportal.model.response.UserExperienceResponse;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.service.UserExperienceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@Tag(name = "UserExperience")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class UserExperienceController {
  private final UserExperienceService service;

  @GetMapping(value = "/users/experiences", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<List<UserExperienceResponse>>> getAll() {
    return ResponseEntity.ok(ResponseHelper.ok(service.getAll()));
  }

  @GetMapping(value = "/users/experiences/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<UserExperienceResponse>> getById(@PathVariable String id) {
    return ResponseEntity.ok(ResponseHelper.ok(service.getById(id)));
  }

  @PostMapping(value = "/users/experiences", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<String>> add(
      @Valid @RequestBody CreateUserExperienceRequest request) {
    service.create(request);
    return ResponseEntity.ok(ResponseHelper.ok("User Experience has been added successfully"));
  }

  @PutMapping(value = "/users/experiences", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<String>> edit(
      @Valid @RequestBody UpdateUserExperienceRequest request) {
    service.update(request);
    return ResponseEntity.ok(ResponseHelper.ok("User Experience has been Updated successfully"));
  }

  @DeleteMapping(value = "/users/experiences/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<String>> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.ok(ResponseHelper.ok("User Experience has been deleted successfully"));
  }

}
