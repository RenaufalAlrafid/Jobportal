package com.lawencon.jobportal.service.impl;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.model.request.file.CreateFileResponse;
import com.lawencon.jobportal.model.response.file.FileResponse;
import com.lawencon.jobportal.persistence.entity.File;
import com.lawencon.jobportal.persistence.repository.FileRepository;
import com.lawencon.jobportal.service.FileService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {
  private final FileRepository repository;

  @Override
  public File create(CreateFileResponse request) {
    File file = new File();
    file.setName(request.getName());
    file.setExtension(request.getExtension());
    file.setFile(request.getFile());
    file.setIsActive(true);
    file.setVersion(0L);
    return repository.save(file);
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
    response.setExtension(file.getExtension());
    response.setFile(file.getFile());
    return response;
  }
}
