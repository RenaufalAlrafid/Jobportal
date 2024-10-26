package com.lawencon.jobportal.service.impl;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.authentication.helper.SessionHelper;
import com.lawencon.jobportal.helper.MappingUtil;
import com.lawencon.jobportal.model.request.userprofile.CreateUserProfileRequest;
import com.lawencon.jobportal.model.request.userprofile.UpdateUserProfileRequest;
import com.lawencon.jobportal.model.response.userprofile.UserProfileResponse;
import com.lawencon.jobportal.persistence.entity.User;
import com.lawencon.jobportal.persistence.entity.UserProfile;
import com.lawencon.jobportal.persistence.repository.UserProfileRepository;
import com.lawencon.jobportal.service.GenderService;
import com.lawencon.jobportal.service.UserProfileService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
  private final UserProfileRepository repository;
  private final GenderService genderService;

  @Override
  public void create(CreateUserProfileRequest request) {
    UserProfile profile = new UserProfile();
    MappingUtil.map(request, profile);
    profile.setDateOfBirth(LocalDate.parse(request.getBirthDate()));
    profile.setUser(request.getUser());
    profile.setGender(genderService.getEntityById(request.getGenderId()));
    profile.setIsActive(true);
    profile.setVersion(0L);
    repository.save(profile);
  }

  @Override
  public UserProfileResponse getByUser() {
    UserProfile profile = getEntityByUser();
    UserProfileResponse response = new UserProfileResponse();
    MappingUtil.map(profile, response);
    response.setGender(profile.getGender().getName());
    response.setDateOfBirth(profile.getDateOfBirth().toString());

    return response;
  }

  @Override
  public void update(UpdateUserProfileRequest request) {
    UserProfile profile = getEntityByUser();
    MappingUtil.map(request, profile);
    profile.setDateOfBirth(LocalDate.parse(request.getBirthDate()));
    profile.setGender(genderService.getEntityById(request.getGenderId()));
    profile.setVersion(profile.getVersion() + 1);
    profile.setIsActive(true);
    repository.saveAndFlush(profile);
  }

  @Override
  public void deleteByUser(User user) {
    UserProfile userProfile = repository.findByUserId(user.getId()).get();
    repository.delete(userProfile);
  }

  @Override
  public UserProfile getEntityByUser() {
    User user = SessionHelper.getLoginUser();
    Optional<UserProfile> optional = repository.findByUserId(user.getId());
    if (!optional.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User profile not found");
    }
    return optional.get();
  }
}
