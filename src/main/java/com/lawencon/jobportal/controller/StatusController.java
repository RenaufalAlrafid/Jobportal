package com.lawencon.jobportal.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.jobportal.authentication.helper.SessionHelper;
import com.lawencon.jobportal.helper.ResponseHelper;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.service.StatusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Auth", description = "Auth API endpoint")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class StatusController {
  private final StatusService service;

  @GetMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<List<ConstantResponse>>> findAll() {
    SessionHelper.validateRole("SA");
    return ResponseEntity.ok(ResponseHelper.ok(service.getAll()));
  }

  @GetMapping(value = "/status/vacancy", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<List<ConstantResponse>>> findVacancyStatus() {
    return ResponseEntity.ok(ResponseHelper.ok(service.getStatusVacancy()));
  }

  @GetMapping(value = "/status/stage", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<List<ConstantResponse>>> findStageStatus() {
    return ResponseEntity.ok(ResponseHelper.ok(service.getStatusStage()));
  }
}
