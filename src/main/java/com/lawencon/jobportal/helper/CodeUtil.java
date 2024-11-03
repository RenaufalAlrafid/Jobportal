package com.lawencon.jobportal.helper;


import java.security.SecureRandom;

public class CodeUtil {
  private static final String ALPHANUMERIC =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  private static final SecureRandom RANDOM = new SecureRandom();

  public static String generateCode(int length, String name) {

    StringBuilder result = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int randomIndex = RANDOM.nextInt(ALPHANUMERIC.length());
      result.append(ALPHANUMERIC.charAt(randomIndex));
    }

    return name + "-" + result.toString();
  }

  public static String generateOtp() {

    StringBuilder result = new StringBuilder(5);

    for (int i = 0; i < 4; i++) {
      int randomIndex = RANDOM.nextInt(ALPHANUMERIC.length());
      result.append(ALPHANUMERIC.charAt(randomIndex));
    }

    return result.toString();
  }
}

