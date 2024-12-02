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
import com.lawencon.jobportal.model.request.CreateCertificationRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.UpdateCertificationRequest;
import com.lawencon.jobportal.model.response.CertificationResponse;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.service.CertificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@Tag(name = "Certification")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class CertificationController {
  private final CertificationService service;

  @GetMapping(value = "/users/certifications", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<List<CertificationResponse>>> getAll(
      PagingRequest pagingRequest, String inquiry) {
    return ResponseEntity
        .ok(ResponseHelper.ok(pagingRequest, service.getAll(pagingRequest, inquiry)));
  }

  @GetMapping(value = "/users/certifications/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<CertificationResponse>> getById(@PathVariable String id) {
    return ResponseEntity.ok(ResponseHelper.ok(service.getById(id)));
  }

  @PostMapping(value = "/users/certifications", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<String>> add(
      @Valid @RequestBody CreateCertificationRequest request) {
    service.create(request);
    return ResponseEntity.ok(ResponseHelper.ok("Certification has been added successfully"));
  }

  @PutMapping(value = "/users/certifications", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<String>> edit(
      @Valid @RequestBody UpdateCertificationRequest request) {
    service.update(request);
    return ResponseEntity.ok(ResponseHelper.ok("Certification has been Updated successfully"));
  }

  @DeleteMapping(value = "/users/certifications/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<String>> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.ok(ResponseHelper.ok("Certification has been deleted successfully"));
  }

}
