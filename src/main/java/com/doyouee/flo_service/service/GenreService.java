package com.doyouee.flo_service.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doyouee.flo_service.controller.GenreController;
import com.doyouee.flo_service.entity.GenreEntity;
import com.doyouee.flo_service.repository.GenreRepository;

@Service
public class GenreService {
    @Autowired GenreRepository genreRepo;

    public Map<String, Object> getGenreList(String keyword, Pageable pageable) {
        Page<GenreEntity> page = genreRepo.findByGenreIdContains(keyword, pageable);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("list", page.getContent());
        map.put("total", page.getTotalElements());
        map.put("totalPage", page.getTotalPages());
        map.put("currentPage", page.getNumber());
        return map;
    }
}
