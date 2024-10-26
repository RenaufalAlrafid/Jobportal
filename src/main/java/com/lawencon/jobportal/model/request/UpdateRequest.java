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
public class UpdateRequest {
  @NotNull(message = "ID cannot be null")
  @NotBlank(message = "ID cannot be empty")
  private String id;

  @NotNull(message = "Version cannot be null")
  private Long version;

  @NotNull(message = "isActive cannot be null")
  private Boolean isActive;

}
