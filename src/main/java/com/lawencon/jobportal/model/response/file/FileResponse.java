package com.lawencon.jobportal.model.response.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
  private String id;
  private String name;
  private String extension;
  private String file;
}
