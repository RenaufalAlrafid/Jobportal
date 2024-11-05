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
import com.lawencon.jobportal.helper.ResponseHelper;
import com.lawencon.jobportal.model.request.CreateMasterRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.UpdateMasterRequest;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.service.LocationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@Tag(name = "Location")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class LocationController {
  private final LocationService service;

  @GetMapping(value = "/location", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA"})
  public ResponseEntity<WebResponse<List<ConstantResponse>>> getAll(
      @Valid PagingRequest pagingRequest, @RequestParam(required = false) String inquiry) {
    return ResponseEntity
        .ok(ResponseHelper.ok(pagingRequest, service.getAll(pagingRequest, inquiry)));
  }

  @GetMapping(value = "/location/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<ConstantResponse>> getById(@PathVariable String id) {
    return ResponseEntity.ok(ResponseHelper.ok(service.getByid(id)));
  }

  @PostMapping(value = "/location", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA"})
  public ResponseEntity<WebResponse<String>> add(@Valid @RequestBody CreateMasterRequest request) {
    service.create(request);
    return ResponseEntity.ok(ResponseHelper.ok("Location has been added successfully"));
  }

  @PutMapping(value = "/location", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA"})
  public ResponseEntity<WebResponse<String>> edit(@Valid @RequestBody UpdateMasterRequest request) {
    service.update(request);
    return ResponseEntity.ok(ResponseHelper.ok("Location has been Updated successfully"));
  }

  @DeleteMapping(value = "/location/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA"})
  public ResponseEntity<WebResponse<String>> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.ok(ResponseHelper.ok("Location has been deleted successfully"));
  }

}
