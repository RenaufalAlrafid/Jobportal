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
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.vacancy.CreateVacancyRequest;
import com.lawencon.jobportal.model.request.vacancy.UpdateVacancyRequest;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.model.response.vacancy.ListVacancyResponse;
import com.lawencon.jobportal.model.response.vacancy.VacancyResponse;
import com.lawencon.jobportal.service.VacancyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@Tag(name = "Vacancy")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class VacancyController {
  private final VacancyService service;

  @GetMapping(value = "/vacancy", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<List<ListVacancyResponse>>> getAll(PagingRequest pagingRequest,
      @RequestParam(required = false) String inquiry) {
    SessionHelper.validateRole("SA");
    return ResponseEntity
        .ok(ResponseHelper.ok(pagingRequest, service.getAll(pagingRequest, inquiry)));
  }

  @GetMapping(value = "/vacancy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<VacancyResponse>> getById(@PathVariable String id) {
    return ResponseEntity.ok(ResponseHelper.ok(service.getByid(id)));
  }

  @PostMapping(value = "/vacancy", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> add(@Valid @RequestBody CreateVacancyRequest request) {
    SessionHelper.validateRole("SA");
    service.create(request);
    return ResponseEntity.ok("Vacancy has been added successfully");
  }

  @PutMapping(value = "/vacancy", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> edit(@Valid @RequestBody UpdateVacancyRequest request) {
    SessionHelper.validateRole("SA");
    service.update(request);
    return ResponseEntity.ok("Vacancy has been Updated successfully");
  }

  @DeleteMapping(value = "/vacancy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> delete(@PathVariable String id) {
    SessionHelper.validateRole("SA");
    service.delete(id);
    return ResponseEntity.ok("Vacancy has been deleted successfully");
  }

}
