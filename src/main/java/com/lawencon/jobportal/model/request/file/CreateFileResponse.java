package com.lawencon.jobportal.model.request.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFileResponse {
  private String name;
  private String extension;
  private String file;
}
