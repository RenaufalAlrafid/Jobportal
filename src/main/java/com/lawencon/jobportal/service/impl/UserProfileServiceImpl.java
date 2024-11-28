package com.lawencon.jobportal.service.impl;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.authentication.helper.SessionHelper;
import com.lawencon.jobportal.helper.CodeUtil;
import com.lawencon.jobportal.helper.MappingUtil;
import com.lawencon.jobportal.model.request.CreateFileResponse;
import com.lawencon.jobportal.model.request.CreateUserProfileRequest;
import com.lawencon.jobportal.model.request.RegisterUserRequest;
import com.lawencon.jobportal.model.request.UpdateUserProfileRequest;
import com.lawencon.jobportal.model.response.FileResponse;
import com.lawencon.jobportal.model.response.UserProfileResponse;
import com.lawencon.jobportal.persistence.entity.File;
import com.lawencon.jobportal.persistence.entity.User;
import com.lawencon.jobportal.persistence.entity.UserProfile;
import com.lawencon.jobportal.persistence.repository.UserProfileRepository;
import com.lawencon.jobportal.service.FileService;
import com.lawencon.jobportal.service.GenderService;
import com.lawencon.jobportal.service.UserProfileService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
  private final UserProfileRepository repository;
  private final GenderService genderService;
  private final FileService fileService;

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

  @Override
  public void updateCv(String baseFile) {
    CreateFileResponse file = new CreateFileResponse();
    UserProfile profile = getEntityByUser();
    file.setFile(baseFile);
    String name = profile.getFullName() + CodeUtil.generateCode(4, "CV");
    file.setName(name);
    file.setExtension("pdf");
    File newFile = fileService.create(file);
    profile.setCvFile(newFile);
    profile.setVersion(profile.getVersion() + 1);
    repository.saveAndFlush(profile);
  }

  @Override
  public FileResponse getCv() {
    UserProfile profile = getEntityByUser();
    FileResponse response = fileService.getById(profile.getCvFile().getId());
    return response;
  }

  @Override
  public void validationRegister(RegisterUserRequest request) {
    if (repository.existsByEmail(request.getEmail())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
    }

    if (repository.existsByNik(request.getNik())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NIK already exists");
    }
  }

  @Override
  public UserProfile getByEmail(String email) {
    Optional<UserProfile> profile = repository.findByEmail(email);
    if (!profile.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User profile not found");
    }
    return profile.get();
  }

  @Override
  public UserProfile getEntityByUserEntity(User user) {
    Optional<UserProfile> optional = repository.findByUserId(user.getId());
    if (!optional.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User profile not found");
    }
    return optional.get();
  }


}
