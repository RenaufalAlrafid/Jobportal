package com.lawencon.jobportal.service;

public interface EmailService {
  public void sendHtmlEmail(String name, String to, String token);
}
