package com.lawencon.jobportal.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationRequest {
  @NotBlank(message = "title is required")
  @NotNull(message = "title is required")
  private String title;

  @NotBlank(message = "massage is required")
  @NotNull(message = "massage is required")
  private String massage;
}
