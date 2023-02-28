package com.greenart.flo.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.flo.service.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyAPIController {
    @Autowired CompanyService companyService;

    @GetMapping("/list")
    public ResponseEntity<Object> getCompanyList(@RequestParam @Nullable String keyword, 
        @PageableDefault(size=10, sort="seq", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        if (keyword==null) keyword="";
        return new ResponseEntity<>(companyService.getCompanyList(keyword, pageable), HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<Object> getCompanyDetail(@RequestParam Long no, @RequestParam @Nullable Integer page, @RequestParam @Nullable String keyword) {
        if (page==null) page = 0;
        if (keyword==null) keyword="";
        Map<String, Object> map = companyService.selectCompanyInfo(no);
        if ((Boolean)map.get("status")) {
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> postCompanyUpdate(@RequestParam Long no, @RequestParam String name) {
        Map<String, Object> map = companyService.updateCompanyInfo(no, name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PutMapping("/add")
    public ResponseEntity<Object> postCompanyAdd(@RequestParam String name) {
        Map<String, Object> map = companyService.addCompanyInfo(name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> getCompanyDelete(@RequestParam Long no) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        companyService.deleteCompany(no);
        map.put("message", "기획사 정보를 삭제했습니다.");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
