package com.lawencon.jobportal.service;

import org.springframework.data.domain.Page;
import com.lawencon.jobportal.model.request.CreateVacancyRequest;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.UpdateVacancyRequest;
import com.lawencon.jobportal.model.response.ListVacancyResponse;
import com.lawencon.jobportal.model.response.VacancyResponse;
import com.lawencon.jobportal.persistence.entity.Vacancy;

public interface VacancyService
    extends CrudService<Vacancy, CreateVacancyRequest, UpdateVacancyRequest, VacancyResponse> {
  Page<ListVacancyResponse> getAll(PagingRequest pagingRequest, String inquiry);
}
