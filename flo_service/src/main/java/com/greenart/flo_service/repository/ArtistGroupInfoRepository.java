package com.greenart.flo_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.flo_service.entity.ArtistGroupInfoEntity;

public interface ArtistGroupInfoRepository extends JpaRepository<ArtistGroupInfoEntity, Long> {
    Page<ArtistGroupInfoEntity> findByAgiNameContains(String agiName, Pageable pageable);
}
