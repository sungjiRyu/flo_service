package com.doyouee.flo_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doyouee.flo_service.entity.AdminEntity;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long>{
    public AdminEntity findByAdminIdAndAdminPwd(String adminId, String adminPwd); // 로그인 처리를 위해
    public Integer countByAdminId(String adminId); // 중복체크
    public Page<AdminEntity> findByAdminIdContains(String adminId, Pageable pageable);
    public AdminEntity findByAdminSeq(Long adminSeq); // 회원seq 받아서 해당하는 회원데이터 삭제(상태값 변경)
}
