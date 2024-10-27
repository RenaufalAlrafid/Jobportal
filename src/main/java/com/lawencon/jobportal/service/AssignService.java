package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.request.assign.ChangeStatusAssignRequest;
import com.lawencon.jobportal.model.request.assign.CreateAssignRequest;
import com.lawencon.jobportal.model.response.File;
import com.lawencon.jobportal.model.response.Assign.AssignResponse;
import com.lawencon.jobportal.persistence.entity.Assign;
import net.sf.jasperreports.engine.JRException;

public interface AssignService {
  Assign getEntityById(String id);

  void create(CreateAssignRequest request);

  List<AssignResponse> getAll();

  void changeStatus(ChangeStatusAssignRequest request);

  public File getReport() throws JRException;
}
