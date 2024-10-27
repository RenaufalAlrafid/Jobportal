package com.lawencon.jobportal.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import com.lawencon.jobportal.helper.CodeUtil;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.assign.ChangeStatusAssignRequest;
import com.lawencon.jobportal.model.request.assign.CreateAssignRequest;
import com.lawencon.jobportal.model.response.Assign.AssignResponse;
import com.lawencon.jobportal.persistence.entity.Assign;
import com.lawencon.jobportal.persistence.entity.AssignDetail;
import com.lawencon.jobportal.persistence.entity.Status;
import com.lawencon.jobportal.persistence.entity.User;
import com.lawencon.jobportal.persistence.entity.Vacancy;
import com.lawencon.jobportal.persistence.entity.VacancyTrx;
import com.lawencon.jobportal.persistence.repository.AssignRepository;
import com.lawencon.jobportal.service.AssignDetailService;
import com.lawencon.jobportal.service.AssignService;
import com.lawencon.jobportal.service.StatusService;
import com.lawencon.jobportal.service.UserService;
import com.lawencon.jobportal.service.VacancyService;
import com.lawencon.jobportal.service.VacancyTrxService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssignServiceImpl implements AssignService {
  private AssignRepository repository;
  private VacancyService vacancyService;
  private UserService userService;
  private AssignDetailService assignDetailService;
  private StatusService statusService;
  private VacancyTrxService vacancyTrxService;

  @Override
  public void create(CreateAssignRequest request) {
    Assign assign = new Assign();
    Vacancy vacancy = vacancyService.getEntityById(request.getVacancyId());
    assign.setVacancy(vacancy);
    assign.setVersion(0L);
    assign = repository.save(assign);

    AssignDetail detail = new AssignDetail();
    detail.setAssign(assign);
    User user = userService.getEntityById(request.getPicId());
    detail.setUser(user);
    detail.setVersion(0L);
    assignDetailService.create(detail);

    VacancyTrx trx = new VacancyTrx();
    trx.setAssign(assign);
    Status status = statusService.getByCode("PD");
    trx.setStatus(status);
    trx.setTrxDate(LocalDate.now());
    trx.setTrxNumber(CodeUtil.generateCode(4, "TRXA"));
    trx.setVersion(0L);
    vacancyTrxService.create(trx);
  }

  @Override
  public List<AssignResponse> getAll() {
    List<Assign> assigns = repository.findAll();
    List<AssignResponse> responses = assigns.stream().map(assign -> {
      AssignResponse response = new AssignResponse();
      response.setId(assign.getId());
      response.setVacancy(assign.getVacancy().getJob().getName());
      VacancyTrx trx = vacancyTrxService.getLastTrx(assign.getId());
      response.setStatus(trx.getStatus().getName());
      return response;
    }).toList();
    return responses;
  }

  @Override
  public void changeStatus(ChangeStatusAssignRequest request) {
    Assign assign = getEntityById(request.getId());
    VacancyTrx trx = new VacancyTrx();
    trx.setAssign(assign);
    Status status = statusService.getEntityById(request.getStatusId());
    trx.setStatus(status);
    trx.setTrxDate(LocalDate.now());
    trx.setTrxNumber(CodeUtil.generateCode(4, "TRXA"));
    trx.setVersion(0L);
    vacancyTrxService.create(trx);
  }

  @Override
  public Assign getEntityById(String id) {
    ValidationUtil.idIsExist(id, repository, "Assign");
    return repository.findById(id).get();
  }

}
