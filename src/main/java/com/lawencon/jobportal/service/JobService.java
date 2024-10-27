package com.lawencon.jobportal.service;

import org.springframework.data.domain.Page;
import com.lawencon.jobportal.model.request.CreateMasterRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.UpdateMasterRequest;
import com.lawencon.jobportal.model.request.description.CreateJobDesc;
import com.lawencon.jobportal.model.request.specification.CreateJobSpec;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.model.response.job.JobResponse;
import com.lawencon.jobportal.persistence.entity.Job;

public interface JobService
    extends CrudService<Job, CreateMasterRequest, UpdateMasterRequest, JobResponse> {
  Page<ConstantResponse> getAll(PagingRequest pagingRequest, String inquiry);

  void createSpec(CreateJobSpec request);

  void createDesc(CreateJobDesc request);
}
