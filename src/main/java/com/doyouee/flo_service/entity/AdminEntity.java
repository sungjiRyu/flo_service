package com.doyouee.flo_service.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@DynamicInsert
@Table(name = "admin_info")
public class AdminEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_seq")         private Long adminSeq;
    @Column(name = "admin_id")          private String adminId;
    @Column(name = "admin_pwd")         private String adminPwd;
    @Column(name = "admin_name")        private String adminName;
    @Column(name = "admin_status")      @ColumnDefault("1")     private Integer adminStatus;
    @Column(name = "admin_grade")       private Integer adminGrade;

}
