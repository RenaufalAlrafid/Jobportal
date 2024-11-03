package com.lawencon.jobportal.service.impl;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.CreateOtpRequest;
import com.lawencon.jobportal.model.response.OtpResponse;
import com.lawencon.jobportal.persistence.entity.Otp;
import com.lawencon.jobportal.persistence.repository.OtpRepository;
import com.lawencon.jobportal.service.OtpService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService {
  private final OtpRepository repository;

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


}
