package com.lawencon.jobportal.service;

import com.lawencon.jobportal.model.request.file.CreateFileResponse;
import com.lawencon.jobportal.model.response.file.FileResponse;
import com.lawencon.jobportal.persistence.entity.File;

public interface FileService {
  File create(CreateFileResponse request);

  File getEntityById(String id);

  FileResponse getById(String id);
}
