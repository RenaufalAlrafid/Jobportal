package com.lawencon.jobportal.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.model.request.CreateFileRequest;
import com.lawencon.jobportal.model.response.FileResponse;
import com.lawencon.jobportal.persistence.entity.File;
import com.lawencon.jobportal.persistence.repository.FileRepository;
import com.lawencon.jobportal.service.FileService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {
  private final FileRepository repository;

  @Override
  public File create(CreateFileRequest request) {
    String url = uploadToDiscord(request.getFile());
    File file = new File();
    file.setName(request.getName());
    file.setUrl(url);
    file.setIsActive(true);
    file.setVersion(0L);
    return repository.save(file);
  }

  private String webHook() {
    return "https://discord.com/api/webhooks/1311517417624834078/gbiTU9846RyjmQuWE_UNREdMm-HxMOD_6TcGwy-yUJDnPqASuqKGE0UM130epTl51C3d";
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private String uploadToDiscord(MultipartFile file) {
    final long MAX_FILE_SIZE = 25 * 1024 * 1024;
    if (file.getSize() > MAX_FILE_SIZE) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File size must not exceed 25 MB");
    }
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.MULTIPART_FORM_DATA);

      MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
      body.add("file", new ByteArrayResource(file.getBytes()) {
        @Override
        public String getFilename() {
          return file.getOriginalFilename();
        }
      });

      HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<Map> response =
          restTemplate.postForEntity(webHook(), requestEntity, Map.class);

      if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
        Map<String, Object> responseBody = response.getBody();
        List<Map<String, Object>> attachments =
            (List<Map<String, Object>>) responseBody.get("attachments");
        if (attachments != null && !attachments.isEmpty()) {
          return (String) attachments.get(0).get("url");
        } else {
          throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
              "Error While Uploading File");
        }
      } else {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
            "Error While Uploading File");
      }
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Error While Uploading File");
    }
  }


  @Override
  public File getEntityById(String id) {
    Optional<File> file = repository.findById(id);
    if (!file.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File not found");
    }
    return file.get();
  }

  @Override
  public FileResponse getById(String id) {
    File file = getEntityById(id);
    FileResponse response = new FileResponse();
    response.setId(file.getId());
    response.setName(file.getName());

    return response;
  }
}
