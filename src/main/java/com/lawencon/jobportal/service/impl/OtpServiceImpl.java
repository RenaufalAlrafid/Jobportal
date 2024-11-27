package com.lawencon.jobportal.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.helper.CodeUtil;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.CreateOtpRequest;
import com.lawencon.jobportal.model.request.VerifyOtpRequest;
import com.lawencon.jobportal.model.response.OtpResponse;
import com.lawencon.jobportal.persistence.entity.Otp;
import com.lawencon.jobportal.persistence.entity.UserProfile;
import com.lawencon.jobportal.persistence.repository.OtpRepository;
import com.lawencon.jobportal.service.EmailService;
import com.lawencon.jobportal.service.OtpService;
import com.lawencon.jobportal.service.UserProfileService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService {
  private final OtpRepository repository;
  private final UserProfileService userProfileService;
  private final EmailService emailService;

  @Override
  public Otp create(CreateOtpRequest request) {
    Otp otp = new Otp();
    otp.setUser(request.getUser());
    otp.setOtp(request.getOtp());
    otp.setExpiredAt(LocalDateTime.now().plusMinutes(15));
    otp.setVersion(0L);
    otp = repository.save(otp);
    return otp;
  }

  @Override
  public Otp getEntityById(String id) {
    ValidationUtil.idIsExist(id, repository, "OTP");
    return repository.findById(id).get();
  }

  @Override
  public OtpResponse getById(String id) {
    Otp otp = getEntityById(id);
    OtpResponse response = new OtpResponse(otp.getId(), otp.getOtp(), otp.getUser().getUsername());
    return response;

  }

  @Override
  public void otpVerify(VerifyOtpRequest request) {
    UserProfile userProfile = userProfileService.getByEmail(request.getEmail());
    Optional<Otp> otpData = repository.findByOtp(request.getOtp());
    if (!otpData.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Wrong OTP . Please check your Email");
    }

    Otp otp = otpData.get();
    if (otp == null || otp.getExpiredAt().isBefore(LocalDateTime.now())) {
      otp.setOtp(CodeUtil.generateOtp());
      otp.setExpiredAt(LocalDateTime.now().plusMinutes(15));
      emailService.sendHtmlEmail(userProfile.getFullName(), userProfile.getEmail(), otp.getOtp());
      repository.saveAndFlush(otp);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "OTP expired. Please check your Email to get new OTP code");
    }
    if (!otp.getUser().getId().equals(userProfile.getUser().getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid OTP for this user");
    }
  }



}
