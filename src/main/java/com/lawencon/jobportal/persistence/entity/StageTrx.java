package com.lawencon.jobportal.persistence.entity;

import java.time.LocalDate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "tb_stage_trxs")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE tb_stage_trxs SET deleted_at = now() WHERE id=? AND version =?")
@Where(clause = "deleted_at IS NULL")
public class StageTrx extends DeletableEntity {
  @ManyToOne
  @JoinColumn(name = "applied_id", nullable = false)
  private Applied applied;

  @ManyToOne
  @JoinColumn(name = "stage_id", nullable = false)
  private Stage stage;

  @ManyToOne
  @JoinColumn(name = "status_id", nullable = false)
  private Status status;

  @ManyToOne
  @JoinColumn(name = "hr_file_id", nullable = true)
  private File hrFile;

  @ManyToOne
  @JoinColumn(name = "candidate_file_id", nullable = true)
  private File candidateFile;

  @Column(name = "trx_date", nullable = false)
  private LocalDate trxDate;

  @Column(name = "trx_number", nullable = false)
  private String trxNumber;

  @Column(name = "score", nullable = true)
  private Integer score;

  @Column(name = "date", nullable = true)
  private LocalDate date;
}
