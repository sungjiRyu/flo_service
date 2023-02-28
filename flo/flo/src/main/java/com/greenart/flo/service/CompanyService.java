package com.greenart.flo.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenart.flo.entity.CompanyEntity;
import com.greenart.flo.repository.CompanyRepository;

@Service
public class CompanyService {
    @Autowired CompanyRepository companyRepository;

    public Map<String, Object> getCompanyList(String keyword, Pageable pageable) {
        Page<CompanyEntity> page = companyRepository.findByNameContains(keyword, pageable);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("list", page.getContent());
        map.put("total", page.getTotalElements());
        map.put("totalPage", page.getTotalPages());
        map.put("currentPage", page.getNumber());
        return map;
    }

    public Map<String, Object> addCompanyInfo(String name) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (companyRepository.countByName(name)==0) {
            // 입력한 이름의 기획사가 없음
            CompanyEntity entity = CompanyEntity.builder().name(name).build();
            companyRepository.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "기획사정보를 추가하였습니다.");
        }
        else {
            // 입력한 이름의 기획사가 있음
            resultMap.put("status", false);
            resultMap.put("message", name+"기획사는 이미 존재합니다.");
        }
        return resultMap;
    }

    @Transactional
    public void deleteCompany(Long company_no) {
        companyRepository.deleteById(company_no);
    }

    public Map<String, Object> selectCompanyInfo(Long company_no) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Optional<CompanyEntity> entityOpt = companyRepository.findById(company_no);
        if (entityOpt.isEmpty()) {
            resultMap.put("status", false);
        }
        else {
            resultMap.put("status", true);
            resultMap.put("no", entityOpt.get().getSeq());
            resultMap.put("name", entityOpt.get().getName());
        }
        return resultMap;
    }

    public Map<String, Object> updateCompanyInfo(Long no, String name) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Optional<CompanyEntity> entityOpt = companyRepository.findById(no);
        if(entityOpt.isEmpty()) {
            resultMap.put("status", false);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", "잘못된 기획사 정보입니다.");
        }
        else if (entityOpt.get().getName().equalsIgnoreCase(name)) {
            resultMap.put("status", false);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", "기존 설정된 이름으로 변경 불가능합니다.");
        }
        else if (companyRepository.countByName(name)!=0) {
            resultMap.put("status", false);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", name+"기획사는 이미 존재합니다.");
        }
        else {
            CompanyEntity entity = CompanyEntity.builder().seq(no).name(name).build();
            companyRepository.save(entity);
            resultMap.put("status", true);
        }
        return resultMap;
    }
}
