package com.lawencon.jobportal.service.impl;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import com.lawencon.jobportal.config.RabbitMQConfig;
import com.lawencon.jobportal.helper.CodeUtil;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.AssignReportRequest;
import com.lawencon.jobportal.model.request.ChangeStatusAssignRequest;
import com.lawencon.jobportal.model.request.CreateAppliedRequest;
import com.lawencon.jobportal.model.request.CreateAssignRequest;
import com.lawencon.jobportal.model.request.CreateNotificationRequest;
import com.lawencon.jobportal.model.response.AssignResponse;
import com.lawencon.jobportal.model.response.File;
import com.lawencon.jobportal.persistence.entity.Assign;
import com.lawencon.jobportal.persistence.entity.AssignDetail;
import com.lawencon.jobportal.persistence.entity.Status;
import com.lawencon.jobportal.persistence.entity.User;
import com.lawencon.jobportal.persistence.entity.Vacancy;
import com.lawencon.jobportal.persistence.entity.VacancyTrx;
import com.lawencon.jobportal.persistence.repository.AssignRepository;
import com.lawencon.jobportal.service.AppliedService;
import com.lawencon.jobportal.service.AssignDetailService;
import com.lawencon.jobportal.service.AssignService;
import com.lawencon.jobportal.service.StatusService;
import com.lawencon.jobportal.service.UserService;
import com.lawencon.jobportal.service.VacancyService;
import com.lawencon.jobportal.service.VacancyTrxService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@AllArgsConstructor
public class AssignServiceImpl implements AssignService {
  private final AssignRepository repository;
  private final VacancyService vacancyService;
  private final UserService userService;
  private final AssignDetailService assignDetailService;
  private final StatusService statusService;
  private final VacancyTrxService vacancyTrxService;
  private final RabbitTemplate rabbitTemplate;
  private final AppliedService appliedService;

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
    detail.setPic(user);
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

    CreateNotificationRequest notif = new CreateNotificationRequest();
    notif.setTitle("Lowongan Baru" + vacancy.getJob().getName());
    notif.setMassage("Lowongan " + vacancy.getJob().getName() + " Telah Ditambahkan");
    rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NOTIF, "assign.notif", notif);
    System.out.println("yolo");
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

  @Override
  public File getReport() throws JRException {
    File file = new File();
    file.setFileName("Report Assign Vacancy");
    file.setFileExt(".pdf");

    List<Assign> assigns = repository.findAll();

    List<AssignReportRequest> reportData = assigns.stream().map(assign -> {
      AssignReportRequest request = new AssignReportRequest();
      request.setId(assign.getId());
      request.setCode(assign.getVacancy().getCode());
      request.setTitle(assign.getVacancy().getJob().getName());
      request.setType(assign.getVacancy().getType().getName());
      request.setLoc(assign.getVacancy().getLocation().getName());
      request.setLevel(assign.getVacancy().getLevel().getName());
      request.setSalary(
          assign.getVacancy().getSalaryStart() + " - " + assign.getVacancy().getSalaryEnd());
      VacancyTrx trx = vacancyTrxService.getLastTrx(assign.getId());
      request.setStatus(trx.getStatus().getName());
      return request;
    }).toList();

    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("param", "Semua Data");
    parameters.put("tableData", dataSource.cloneDataSource());

    InputStream filePath =
        getClass().getClassLoader().getResourceAsStream("templates/vacancy.jrxml");
    JasperReport report = JasperCompileManager.compileReport(filePath);
    JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
    byte[] bytes = JasperExportManager.exportReportToPdf(print);
    file.setData(bytes);
    return file;
  }

  @Override
  public void CreateApplied(CreateAppliedRequest request) {
    Assign assign = getEntityById(request.getAssignId());
    appliedService.create(assign);
  }

}
