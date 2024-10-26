package com.lawencon.jobportal.service;

import com.lawencon.jobportal.model.request.userprofile.CreateUserProfileRequest;
import com.lawencon.jobportal.model.request.userprofile.UpdateUserProfileRequest;
import com.lawencon.jobportal.model.response.userprofile.UserProfileResponse;
import com.lawencon.jobportal.persistence.entity.User;
import com.lawencon.jobportal.persistence.entity.UserProfile;

public interface UserProfileService {

  void create(CreateUserProfileRequest request);

  UserProfile getEntityByUser();

  UserProfileResponse getByUser();

  void update(UpdateUserProfileRequest request);

  void deleteByUser(User user);
}
