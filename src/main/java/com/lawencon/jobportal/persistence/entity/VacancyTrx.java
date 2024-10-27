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
@Table(name = "tb_vacancy_status_trx")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE tb_vacancy_status_trx SET deleted_at = now() WHERE id=? AND version =?")
@Where(clause = "deleted_at IS NULL")
public class VacancyTrx extends DeletableEntity {
        @ManyToOne
        @JoinColumn(name = "assign_id", nullable = false)
        private Assign assign;

        @ManyToOne
        @JoinColumn(name = "status_id", nullable = false)
        private Status status;

        @Column(name = "trx_date", nullable = false)
        private LocalDate trxDate;

        @Column(name = "trx_number", nullable = false)
        private String trxNumber;
}
