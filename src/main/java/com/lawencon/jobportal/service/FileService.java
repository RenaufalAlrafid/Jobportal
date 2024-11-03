package com.lawencon.jobportal.service;

import com.lawencon.jobportal.model.request.CreateFileResponse;
import com.lawencon.jobportal.model.response.FileResponse;
import com.lawencon.jobportal.persistence.entity.File;

public interface FileService {
  File create(CreateFileResponse request);

  File getEntityById(String id);

  FileResponse getById(String id);
}
