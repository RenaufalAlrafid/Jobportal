package com.lawencon.jobportal.service;

import java.util.List;
import com.lawencon.jobportal.model.request.ChangeStatusAssignRequest;
import com.lawencon.jobportal.model.request.CreateAppliedRequest;
import com.lawencon.jobportal.model.request.CreateAssignRequest;
import com.lawencon.jobportal.model.response.AssignResponse;
import com.lawencon.jobportal.model.response.File;
import com.lawencon.jobportal.persistence.entity.Assign;
import net.sf.jasperreports.engine.JRException;

public interface AssignService {
  Assign getEntityById(String id);

  void create(CreateAssignRequest request);

  List<AssignResponse> getAll();

  void changeStatus(ChangeStatusAssignRequest request);

  File getReport() throws JRException;

  void CreateApplied(CreateAppliedRequest request);


}
