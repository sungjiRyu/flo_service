package com.doyouee.flo_service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doyouee.flo_service.service.CompanyService;

import org.springframework.data.domain.Sort;


import jakarta.annotation.Nullable;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired CompanyService companyService;

    @GetMapping("/list")
    public String getCompanyList(Model model, @RequestParam @Nullable String keyword, @PageableDefault(size=10, sort="companySeq", direction = Sort.Direction.DESC) 
        Pageable pageable)
        {
            if(keyword == null) keyword = "";
            model.addAttribute("result", companyService.getCompanyList(keyword, pageable));
            model.addAttribute("keyword", keyword);
            return "/company/list";
    }
    @GetMapping("/add")
    public String getcompanyAdd(){
        return "/company/add";
    }
    @GetMapping("/detail")
    public String getcompanyDetail(@RequestParam Long company_no, @RequestParam @Nullable Integer page, @RequestParam @Nullable String keyword, Model model){
        if(page == null) page = 0;
        if(keyword == null) keyword = "";
        Map<String,Object> map = companyService.selectCompanyInfo(company_no);
        map.put("message", null);
        model.addAttribute("company", map);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        
        return "/company/detail";
    }

    @PostMapping("/update")
    public String postcompanyUpdate(Long no, String name, Model model){
        Map<String, Object>map = companyService.updateCompanyInfo(no, name);
        if((Boolean)map.get("updated")){
            return "redirect:/company/list";
        }
            else{
                map.put("status", true);
                model.addAttribute("company", map);
                return "/company/detail";
            }
    }

    @PostMapping("/add")
    public String postcompanyAdd(String name, Model model){
    Map<String, Object>map = companyService.addCompanyInfo(name);
    if((Boolean)map.get("updated")){
    return "redirect:/company/list";
    }
    else{
        model.addAttribute("name", name);
        model.addAttribute("result", map);
        return "/company/add";
    }
    }

    @GetMapping("/delete")
    public String getcompanyDelete(@RequestParam Long company_no, @RequestParam @Nullable Integer page, @RequestParam @Nullable String Keyword, Model model){
        if(page == null) page = 0;
        Map<String, Object> map = companyService.selectCompanyInfo(company_no);
        model.addAttribute("company", map);
        model.addAttribute("page", page);
        model.addAttribute("keyword", Keyword);
        companyService.deleteCompany(company_no);
        return "redirect:/company/list";
    }
}
