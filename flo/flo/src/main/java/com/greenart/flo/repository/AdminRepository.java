package com.greenart.flo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.greenart.flo.entity.AdminEntity;

@Repository
public interface AdminRepository extends JpaRepositoryImplementation<AdminEntity, Long> {
    public AdminEntity findByAdminId(String adminId);
    public AdminEntity findByAdminIdAndAdminPwd(String adminId, String adminPwd);
    public Integer countByAdminId(String adminId);
    public Page<AdminEntity> findByAdminIdContains(String adminId, Pageable pageable);
}
