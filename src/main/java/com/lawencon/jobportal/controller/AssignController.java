package com.lawencon.jobportal.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.jobportal.helper.ResponseHelper;
import com.lawencon.jobportal.model.request.ChangeStatusAssignRequest;
import com.lawencon.jobportal.model.request.CreateAssignRequest;
import com.lawencon.jobportal.model.response.AssignResponse;
import com.lawencon.jobportal.model.response.File;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.service.AssignService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;


@Tag(name = "Assign", description = "Auth API endpoint")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class AssignController {
  private final AssignService service;

  @GetMapping(value = "/assign", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<List<AssignResponse>>> getAll() {
    return ResponseEntity.ok(ResponseHelper.ok(service.getAll()));
  }

  @PostMapping(value = "/assign", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA"})
  public ResponseEntity<String> add(@Valid @RequestBody CreateAssignRequest request) {
    service.create(request);
    return ResponseEntity.ok("Assign has been added successfully");
  }

  @PostMapping(value = "/assign/status", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA", "HR"})
  public ResponseEntity<String> add(@Valid @RequestBody ChangeStatusAssignRequest request) {
    service.changeStatus(request);
    return ResponseEntity.ok("Vacancy Status has been Updated successfully");
  }

  @GetMapping(value = "/assign/report", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA", "HR"})
  public ResponseEntity<WebResponse<File>> generateReport() throws JRException {
    return ResponseEntity.ok(ResponseHelper.ok(service.getReport()));
  }

}
