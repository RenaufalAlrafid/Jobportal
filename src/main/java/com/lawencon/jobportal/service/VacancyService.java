package com.lawencon.jobportal.service;

import org.springframework.data.domain.Page;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.vacancy.CreateVacancyRequest;
import com.lawencon.jobportal.model.request.vacancy.UpdateVacancyRequest;
import com.lawencon.jobportal.model.response.vacancy.ListVacancyResponse;
import com.lawencon.jobportal.model.response.vacancy.VacancyResponse;
import com.lawencon.jobportal.persistence.entity.Vacancy;

public interface VacancyService
    extends CrudService<Vacancy, CreateVacancyRequest, UpdateVacancyRequest, VacancyResponse> {
  Page<ListVacancyResponse> getAll(PagingRequest pagingRequest, String inquiry);
}
