package com.lawencon.jobportal.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.jobportal.helper.ResponseHelper;
import com.lawencon.jobportal.model.request.UpdateUserProfileRequest;
import com.lawencon.jobportal.model.request.UploadPhotoRequest;
import com.lawencon.jobportal.model.response.FileResponse;
import com.lawencon.jobportal.model.response.UserProfileResponse;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.service.UserProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;



@Tag(name = "User Profile")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class UserProfileController {
  private final UserProfileService service;


  @GetMapping(value = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<UserProfileResponse>> getById() {
    return ResponseEntity.ok(ResponseHelper.ok(service.getByUser()));
  }


  @PutMapping(value = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<String>> edit(
      @Valid @RequestBody UpdateUserProfileRequest request) {
    service.update(request);
    return ResponseEntity.ok(ResponseHelper.ok("User Profile has been Updated successfully"));
  }

  @GetMapping(value = "/profiles/cv", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<FileResponse>> getFile() {
    return ResponseEntity.ok(ResponseHelper.ok(service.getCv()));
  }

  @PostMapping(value = "/profiles/cv", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<String>> uploadCv(@Valid @RequestBody String request) {
    service.updateCv(request);
    return ResponseEntity.ok(ResponseHelper.ok("CV has been Updated successfully"));
  }

  @PutMapping(value = "/profiles/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<WebResponse<String>> updatePhoto(
      @ModelAttribute UploadPhotoRequest request) {
    service.uploadFile(request);
    return ResponseEntity.ok(ResponseHelper.ok("Photo Has been Uploaded successfully"));
  }

}
