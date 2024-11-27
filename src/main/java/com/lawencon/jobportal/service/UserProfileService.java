package com.lawencon.jobportal.service;

import com.lawencon.jobportal.model.request.CreateUserProfileRequest;
import com.lawencon.jobportal.model.request.RegisterUserRequest;
import com.lawencon.jobportal.model.request.UpdateUserProfileRequest;
import com.lawencon.jobportal.model.response.FileResponse;
import com.lawencon.jobportal.model.response.UserProfileResponse;
import com.lawencon.jobportal.persistence.entity.User;
import com.lawencon.jobportal.persistence.entity.UserProfile;

public interface UserProfileService {

  void create(CreateUserProfileRequest request);

  UserProfile getEntityByUser();

  UserProfileResponse getByUser();

  void update(UpdateUserProfileRequest request);

  void deleteByUser(User user);

  void updateCv(String file);

  FileResponse getCv();

  void validationRegister(RegisterUserRequest request);

  UserProfile getByEmail(String email);
}
