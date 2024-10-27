package com.lawencon.jobportal.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.jobportal.helper.ResponseHelper;
import com.lawencon.jobportal.model.request.userprofile.UpdateUserProfileRequest;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.model.response.file.FileResponse;
import com.lawencon.jobportal.model.response.userprofile.UserProfileResponse;
import com.lawencon.jobportal.service.UserProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;



@Tag(name = "Role")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class UserProfileController {
  private final UserProfileService service;


  @GetMapping(value = "/user/profile", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<UserProfileResponse>> getById() {
    return ResponseEntity.ok(ResponseHelper.ok(service.getByUser()));
  }


  @PutMapping(value = "/user/profile", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> edit(@Valid @RequestBody UpdateUserProfileRequest request) {
    service.update(request);
    return ResponseEntity.ok("User Profile has been Updated successfully");
  }

  @GetMapping(value = "/profile/cv", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<FileResponse>> getFile() {
    return ResponseEntity.ok(ResponseHelper.ok(service.getCv()));
  }

  @PostMapping(value = "/profile/cv", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> uploadCv(@Valid @RequestBody String request) {
    service.updateCv(request);
    return ResponseEntity.ok("CV has been Updated successfully");
  }

}
