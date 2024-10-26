package com.lawencon.jobportal.service;

import com.lawencon.jobportal.model.request.userprofile.CreateUserProfileRequest;

public interface UserProfileService {

  void create(CreateUserProfileRequest request);
}
