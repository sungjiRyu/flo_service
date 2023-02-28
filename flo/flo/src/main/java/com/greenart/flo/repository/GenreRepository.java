package com.greenart.flo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.flo.entity.GenreEntity;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    public Page<GenreEntity> findByNameContains(String name, Pageable pageable);
    public Integer countByName(String name);
}
