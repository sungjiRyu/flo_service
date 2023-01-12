package com.doyouee.flo_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Sort;

import com.doyouee.flo_service.service.GenreService;

import jakarta.annotation.Nullable;

@Controller
@RequestMapping("/genre")
public class GenreController {
    @Autowired GenreService genreService;

    @GetMapping("/list")
    public String getGenreList(Model model, @RequestParam @Nullable String keyword, @PageableDefault(size=10, sort="adminSeq", direction = Sort.Direction.DESC) 
        Pageable pageable)
        {
            if(keyword == null) keyword = "";
            model.addAttribute("result", genreService.getGenreList(keyword, pageable));
            model.addAttribute("keyword", keyword);
            return "/genre/list";
    }
    @GetMapping("/add")
    public String getGenreAdd(){
        return "/genre/add";
    }
    @GetMapping("/detail")
    public String getGenreDetail(){
        return "/genre/detail";
    }
}
