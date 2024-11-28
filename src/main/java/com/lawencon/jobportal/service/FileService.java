package com.lawencon.jobportal.service;

import com.lawencon.jobportal.model.request.CreateFileRequest;
import com.lawencon.jobportal.model.response.FileResponse;
import com.lawencon.jobportal.persistence.entity.File;

public interface FileService {
  File create(CreateFileRequest request);

  File getEntityById(String id);

  FileResponse getById(String id);
}
