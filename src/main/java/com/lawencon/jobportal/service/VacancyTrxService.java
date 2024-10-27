package com.lawencon.jobportal.service;

import com.lawencon.jobportal.persistence.entity.VacancyTrx;

/**
 * VacancyTrxService
 */
public interface VacancyTrxService {

  void create(VacancyTrx data);

  VacancyTrx getLastTrx(String id);
}
