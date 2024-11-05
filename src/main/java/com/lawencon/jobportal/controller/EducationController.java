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
import com.lawencon.jobportal.model.request.CreateEducationRequest;
import com.lawencon.jobportal.model.request.UpdateEducationRequest;
import com.lawencon.jobportal.model.response.EducationResponse;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.service.EducationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@Tag(name = "Education")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class EducationController {
  private final EducationService service;

  @GetMapping(value = "/educations", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<List<EducationResponse>>> getAll() {
    return ResponseEntity.ok(ResponseHelper.ok(service.getAll()));
  }

  @GetMapping(value = "/educations/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<EducationResponse>> getById(@PathVariable String id) {
    return ResponseEntity.ok(ResponseHelper.ok(service.getById(id)));
  }

  @PostMapping(value = "/educations", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<String>> add(
      @Valid @RequestBody CreateEducationRequest request) {
    service.create(request);
    return ResponseEntity.ok(ResponseHelper.ok("Education has been added successfully"));
  }

  @PutMapping(value = "/educations", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<String>> edit(
      @Valid @RequestBody UpdateEducationRequest request) {
    service.update(request);
    return ResponseEntity.ok(ResponseHelper.ok("Education has been Updated successfully"));
  }

  @DeleteMapping(value = "/educations/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<String>> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.ok(ResponseHelper.ok("Education has been deleted successfully"));
  }

}
