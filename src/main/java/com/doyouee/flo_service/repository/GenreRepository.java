package com.doyouee.flo_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doyouee.flo_service.entity.AdminEntity;
import com.doyouee.flo_service.entity.GenreEntity;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity,Long> {
    // contains => 해당하는 문자열이 포함되어있으면 True 반환 
    public Page<GenreEntity> findByGenreIdContains(String genreId, Pageable pageable);

}
