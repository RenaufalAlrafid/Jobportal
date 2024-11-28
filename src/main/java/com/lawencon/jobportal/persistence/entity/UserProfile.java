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
@Table(name = "tb_user_profiles",
        uniqueConstraints = {
                @UniqueConstraint(name = "profile_ck", columnNames = {"user_id", "code"})})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE tb_roles SET deleted_at = now() WHERE id=? AND version =?")
@Where(clause = "deleted_at IS NULL")
public class UserProfile extends MasterEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "photo_file_id", nullable = true)
    private File photo;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "cv_file_id", nullable = true)
    private File cvFile;

    @Column(name = "nik", nullable = false)
    private String nik;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "city", nullable = true)
    private String city;
}
