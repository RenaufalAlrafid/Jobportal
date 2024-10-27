package com.lawencon.jobportal.persistence.entity;

import java.time.LocalDate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "tb_vacancies",
    uniqueConstraints = {
        @UniqueConstraint(name = "vacancy_ck", columnNames = {"code", "deleted_at"})})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE tb_vacancies SET deleted_at = now() WHERE id=? AND version =?")
@Where(clause = "deleted_at IS NULL")
public class Vacancies extends MasterEntity {
  @ManyToOne
  @JoinColumn(name = "job_id", nullable = false)
  private Job job;

  @ManyToOne
  @JoinColumn(name = "type_id", nullable = false)
  private EmploymentType type;

  @ManyToOne
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;

  @ManyToOne
  @JoinColumn(name = "level_id", nullable = false)
  private Experience level;

  @Column(name = "code", nullable = false)
  private String code;

  @Column(name = "salary_start", nullable = false)
  private Integer salaryStart;

  @Column(name = "salary_end", nullable = false)
  private Integer salaryEnd;

  @Column(name = "due_date", nullable = false)
  private LocalDate dueDate;

  @Column(name = "overview", nullable = false)
  private String overview;



}
