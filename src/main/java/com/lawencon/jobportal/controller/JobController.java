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
import com.lawencon.jobportal.model.request.CreateMasterRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.UpdateMasterRequest;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.service.JobService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@Tag(name = "Job")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class JobController {
  private final JobService service;

  @GetMapping(value = "/job", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<List<ConstantResponse>>> getAll(PagingRequest pagingRequest,
      @RequestParam(required = false) String inquiry) {
    SessionHelper.validateRole("SA");
    return ResponseEntity
        .ok(ResponseHelper.ok(pagingRequest, service.getAll(pagingRequest, inquiry)));
  }

  @GetMapping(value = "/job/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<ConstantResponse>> getById(@PathVariable String id) {
    return ResponseEntity.ok(ResponseHelper.ok(service.getByid(id)));
  }

  @PostMapping(value = "/job", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> add(@Valid @RequestBody CreateMasterRequest request) {
    SessionHelper.validateRole("SA");
    service.create(request);
    return ResponseEntity.ok("Job has been added successfully");
  }

  @PutMapping(value = "/job", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> edit(@Valid @RequestBody UpdateMasterRequest request) {
    SessionHelper.validateRole("SA");
    service.update(request);
    return ResponseEntity.ok("Job has been Updated successfully");
  }

  @DeleteMapping(value = "/job/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> delete(@PathVariable String id) {
    SessionHelper.validateRole("SA");
    service.delete(id);
    return ResponseEntity.ok("Job has been deleted successfully");
  }

}