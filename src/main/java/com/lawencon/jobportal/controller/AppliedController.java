package com.lawencon.jobportal.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.jobportal.helper.ResponseHelper;
import com.lawencon.jobportal.model.request.CreateAppliedRequest;
import com.lawencon.jobportal.model.request.CreateStageRequest;
import com.lawencon.jobportal.model.request.UpdateStageRequest;
import com.lawencon.jobportal.model.response.AppliedResponse;
import com.lawencon.jobportal.model.response.ListAppliedResponse;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.service.AppliedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;



@Tag(name = "Applied", description = "Applied API endpoint")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class AppliedController {
  private final AppliedService service;

  @PostMapping(value = "/applied", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"CDT"})
  public ResponseEntity<String> create(@Valid @RequestBody CreateAppliedRequest request) {
    service.create(request);
    return ResponseEntity.ok("Applied has been added successfully");
  }

  @GetMapping(value = "/applied/assign/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<List<ListAppliedResponse>>> getAllByAssignId(
      @PathVariable String id) {
    return ResponseEntity.ok(ResponseHelper.ok(service.getAllByAssignId(id)));
  }

  @PutMapping(value = "/applied/stage", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"HR"})
  public ResponseEntity<String> updateStage(@Valid @RequestBody UpdateStageRequest request) {
    service.updateStage(request);
    return ResponseEntity.ok("Applied has been update successfully");
  }


  @PostMapping(value = "/applied/stage", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"HR"})
  public ResponseEntity<String> create(@Valid @RequestBody CreateStageRequest request) {
    service.createStage(request);
    return ResponseEntity.ok("Stage has been added successfully");
  }

  @GetMapping(value = "/applied/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<AppliedResponse>> getAppliedById(@PathVariable String id) {
    return ResponseEntity.ok(ResponseHelper.ok(service.getAppliedById(id)));
  }
}
