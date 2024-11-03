package com.lawencon.jobportal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.lawencon.jobportal.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
  private static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
  private static final String UTF_8_ENCODING = "UTF-8";
  private static final String EMAIL_TEMPLATE = "EmailOtp";

  private final JavaMailSender emailSender;
  private final TemplateEngine templateEngine;

  @Value("${spring.mail.username}")
  private String fromEmail;

  @Override
  @Async
  public void sendHtmlEmail(String name, String to, String otp) {
    try {
      Context context = new Context();
      context.setVariable("name", name);
      context.setVariable("otp", otp);

      String text = templateEngine.process(EMAIL_TEMPLATE, context);
      MimeMessage message = emailSender.createMimeMessage();

      MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
      helper.setPriority(1);
      helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
      helper.setFrom(fromEmail);
      helper.setTo(to);
      helper.setText(text, true);

      emailSender.send(message);
    } catch (Exception exception) {
      log.error("Failed to send email to {}: {}", to, exception.getMessage(), exception);
      throw new RuntimeException("Failed to send email", exception);
    }
  }
}
