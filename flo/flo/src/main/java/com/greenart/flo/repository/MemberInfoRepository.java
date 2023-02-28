package com.greenart.flo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.flo.entity.MemberInfoEntity;

public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity, Long> {
    public MemberInfoEntity findByMiIdAndMiPwd(String miId, String miPwd);
    public Integer countByMiId(String miId);
}
