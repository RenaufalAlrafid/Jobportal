package com.lawencon.jobportal.service;

import com.lawencon.jobportal.model.request.CreateOtpRequest;
import com.lawencon.jobportal.model.request.VerifyOtpRequest;
import com.lawencon.jobportal.model.response.OtpResponse;
import com.lawencon.jobportal.persistence.entity.Otp;

public interface OtpService {
  Otp create(CreateOtpRequest request);

  Otp getEntityById(String id);

  OtpResponse getById(String id);

  void otpVerify(VerifyOtpRequest request);
}
