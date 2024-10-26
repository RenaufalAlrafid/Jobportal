package com.lawencon.jobportal.service.impl;

import java.time.LocalDate;
import org.springframework.stereotype.Service;
import com.lawencon.jobportal.helper.MappingUtil;
import com.lawencon.jobportal.model.request.userprofile.CreateUserProfileRequest;
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
    repository.save(profile);
  }
}
