package com.lawencon.jobportal.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.lawencon.jobportal.model.request.CreateMasterRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.UpdateMasterRequest;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.persistence.entity.Location;

public interface LocationService
    extends CrudService<Location, CreateMasterRequest, UpdateMasterRequest, ConstantResponse> {
  Page<ConstantResponse> getAll(PagingRequest pagingRequest, String inquiry);

  List<ConstantResponse> getAllLocation();
}
