package com.greenart.flo_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.flo_service.entity.CompanyEntity;
import com.greenart.flo_service.entity.GenreEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long>{
  public Page<CompanyEntity> findByPubNameContains(String pubName, Pageable pageble);
  public Integer countByPubName(String pubName);
  
}
