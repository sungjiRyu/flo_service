package com.greenart.flo_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.flo_service.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    public AdminEntity findByAdminIdAndAdminPwd(String adminId, String adminPwd);
    public Integer countByAdminId(String adminId);
    public Page<AdminEntity> findByAdminIdContains(String adminId, Pageable pageable);
}
