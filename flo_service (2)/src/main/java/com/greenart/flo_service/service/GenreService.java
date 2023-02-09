package com.greenart.flo_service.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.greenart.flo_service.entity.GenreEntity;
import com.greenart.flo_service.repository.GenreRepository;

import jakarta.transaction.Transactional;

@Service
public class GenreService {
    @Autowired GenreRepository genreRepository;
    public Map<String, Object> getGenreList(String keyword, Pageable pageable) {
        Page<GenreEntity> page = genreRepository.findByGenreNameContains(keyword, pageable);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("list", page.getContent());
        map.put("total", page.getTotalElements());
        map.put("totalPage", page.getTotalPages());
        map.put("currentPage", page.getNumber());
        return map;
    }

    public Map<String, Object> addGenreInfo(String name) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(genreRepository.countByGenreName(name) == 0) {
            // 입력한 이름의 장르가 없음
            GenreEntity entity = GenreEntity.builder().genreName(name).build();
            genreRepository.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "장르정보를 추가하였습니다.");
        }
        else {
            // 입력한 이름의 장르가 있음
            resultMap.put("status", false);
            resultMap.put("message", name+"장르는 이미 존재합니다.");
        }
        return resultMap;
    }

    public Map<String, Object> updateGenreInfo(Long no, String name) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Optional<GenreEntity> entityOpt = genreRepository.findById(no);
        if(entityOpt.isEmpty()) {
            resultMap.put("updated", false);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", "잘못된 장르 정보입니다.");
        }
        else if(entityOpt.get().getGenreName().equalsIgnoreCase(name)){
            resultMap.put("updated", false);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", "기존 설정된 이름으로 변경 불가능합니다.");
        }
        else if(genreRepository.countByGenreName(name) != 0) {
            resultMap.put("updated", false);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", name+"장르는 이미 존재합니다.");
        }
        else {
            GenreEntity entity = GenreEntity.builder().genreSeq(no).genreName(name).build();
            genreRepository.save(entity);
            resultMap.put("updated", true);
            resultMap.put("message", "");
        }
        return resultMap;
    }


    @Transactional
    public void deleteGenre(Long genre_no) {
        genreRepository.deleteById(genre_no);
    }

    public Map<String, Object> selectGenreInfo(Long genre_no) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Optional<GenreEntity> entityOpt = genreRepository.findById(genre_no);
        if(entityOpt.isEmpty()) {
            resultMap.put("status", false);
        }
        else {
            resultMap.put("status", true);
            resultMap.put("no", entityOpt.get().getGenreSeq());
            resultMap.put("name", entityOpt.get().getGenreName());
        }
        return resultMap;
    }
}
