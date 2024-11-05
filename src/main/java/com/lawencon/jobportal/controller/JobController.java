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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.jobportal.authentication.helper.SessionHelper;
import com.lawencon.jobportal.helper.ResponseHelper;
import com.lawencon.jobportal.model.request.CreateJobDesc;
import com.lawencon.jobportal.model.request.CreateJobSpec;
import com.lawencon.jobportal.model.request.CreateMasterRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.UpdateDescriptionRequest;
import com.lawencon.jobportal.model.request.UpdateMasterRequest;
import com.lawencon.jobportal.model.request.UpdateSpecificationRequest;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.model.response.JobResponse;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.service.DescriptionService;
import com.lawencon.jobportal.service.JobService;
import com.lawencon.jobportal.service.SpecificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@Tag(name = "Job")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class JobController {
  private final JobService service;
  private final SpecificationService specificationService;
  private final DescriptionService descriptionService;

  @GetMapping(value = "/job", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA"})
  public ResponseEntity<WebResponse<List<ConstantResponse>>> getAll(
      @Valid PagingRequest pagingRequest, @RequestParam(required = false) String inquiry) {
    return ResponseEntity
        .ok(ResponseHelper.ok(pagingRequest, service.getAll(pagingRequest, inquiry)));
  }

  @GetMapping(value = "/job/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<JobResponse>> getById(@PathVariable String id) {
    return ResponseEntity.ok(ResponseHelper.ok(service.getByid(id)));
  }

  @PostMapping(value = "/job", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA"})
  public ResponseEntity<WebResponse<String>> add(@Valid @RequestBody CreateMasterRequest request) {
    service.create(request);
    return ResponseEntity.ok(ResponseHelper.ok("Job has been added successfully"));
  }

  @PutMapping(value = "/job", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA"})
  public ResponseEntity<WebResponse<String>> edit(@Valid @RequestBody UpdateMasterRequest request) {
    service.update(request);
    return ResponseEntity.ok(ResponseHelper.ok("Job has been Updated successfully"));
  }

  @DeleteMapping(value = "/job/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA"})
  public ResponseEntity<WebResponse<String>> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.ok(ResponseHelper.ok("Job has been deleted successfully"));
  }

  @PostMapping(value = "/job/spec", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA"})
  public ResponseEntity<WebResponse<String>> createSpec(@Valid @RequestBody CreateJobSpec request) {
    service.createSpec(request);
    return ResponseEntity.ok(ResponseHelper.ok("Specification has been added successfully"));
  }

  @PutMapping(value = "/job/spec", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA"})
  public ResponseEntity<WebResponse<String>> updateSpec(
      @Valid @RequestBody UpdateSpecificationRequest request) {
    specificationService.update(request);
    return ResponseEntity.ok(ResponseHelper.ok("Specification has been added successfully"));
  }

  @DeleteMapping(value = "/job/spec/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA"})
  public ResponseEntity<WebResponse<String>> deleteSpec(@PathVariable String id) {
    specificationService.delete(id);
    return ResponseEntity.ok(ResponseHelper.ok("Specification has been deleted successfully"));
  }

  @PostMapping(value = "/job/desc", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<String>> createDesc(@Valid @RequestBody CreateJobDesc request) {
    SessionHelper.validateRole("SA");
    service.createDesc(request);
    return ResponseEntity.ok(ResponseHelper.ok("Description has been added successfully"));
  }

  @PutMapping(value = "/job/desc", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA"})
  public ResponseEntity<WebResponse<String>> updateDesc(
      @Valid @RequestBody UpdateDescriptionRequest request) {
    descriptionService.update(request);
    return ResponseEntity.ok(ResponseHelper.ok("Description has been added successfully"));
  }

  @DeleteMapping(value = "/job/desc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA"})
  public ResponseEntity<WebResponse<String>> deleteDesc(@PathVariable String id) {
    descriptionService.delete(id);
    return ResponseEntity.ok(ResponseHelper.ok("Description has been deleted successfully"));
  }

}
