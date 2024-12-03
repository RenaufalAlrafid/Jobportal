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
import com.lawencon.jobportal.model.request.RegisterUserRequest;
import com.lawencon.jobportal.model.request.UpdateUserRequest;
import com.lawencon.jobportal.model.response.ListUserResponse;
import com.lawencon.jobportal.model.response.UserLoginResponse;
import com.lawencon.jobportal.model.response.UserResponse;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "User")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class UserController {
  private final UserService service;

  @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA", "HR"})
  public ResponseEntity<WebResponse<List<ListUserResponse>>> getAll(
      @Valid PagingRequest pagingRequest, @RequestParam(required = false) String inquiry) {
    return ResponseEntity
        .ok(ResponseHelper.ok(pagingRequest, service.getAll(pagingRequest, inquiry)));
  }

  @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA", "HR"})
  public ResponseEntity<WebResponse<UserResponse>> getById(@PathVariable String id) {
    return ResponseEntity.ok(ResponseHelper.ok(service.getByid(id)));
  }

  @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<String>> add(@Valid @RequestBody RegisterUserRequest request) {
    service.create(request);
    return ResponseEntity.ok(ResponseHelper.ok("User has been added successfully"));
  }

  @PutMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA", "HR"})
  public ResponseEntity<WebResponse<String>> edit(@Valid @RequestBody UpdateUserRequest request) {
    service.update(request);
    return ResponseEntity.ok(ResponseHelper.ok("User has been Updated successfully"));
  }

  @DeleteMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @RolesAllowed({"SA", "HR"})
  public ResponseEntity<WebResponse<String>> delete(@PathVariable String id) {
    SessionHelper.validateRole("SA");
    service.delete(id);
    return ResponseEntity.ok(ResponseHelper.ok("User has been deleted successfully"));
  }

  @GetMapping(value = "/users/login", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<UserLoginResponse>> getUserLogin() {
    return ResponseEntity.ok(ResponseHelper.ok(service.getUserLogin()));
  }

}
