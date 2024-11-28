package com.lawencon.jobportal.model.request;


import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFileRequest {
  private String name;
  private MultipartFile file;
}
