package com.greenart.flo_service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenart.flo_service.service.GenreService;

import io.micrometer.common.lang.Nullable;


@Controller
@RequestMapping("/genre")
public class GenreController {
  @Autowired GenreService genreService;
  @GetMapping("/list")
  public String getGenreList(Model model, @RequestParam @Nullable String keyword,
  @PageableDefault(size=10, sort="genreSeq", direction = Sort.Direction.DESC) Pageable pageable) {
    if(keyword == null) keyword ="";
    model.addAttribute("result", genreService.getGenreList(keyword, pageable));
    model.addAttribute("keyword", keyword);
    return "/genre/list";
  }
  @GetMapping("/add")
  public String getGenreAdd() {
    return "/genre/add";
  }
  @GetMapping("/detail")
  public String getGenreDetail(@RequestParam Long genre_no, 
  @RequestParam @Nullable Integer page, 
  @RequestParam @Nullable String keyword, Model model) {
    if(page == null) page = 0;
    if(keyword == null) keyword = "";
    Map<String, Object> map = genreService.selectGenreInfo(genre_no);
    map.put("message", null);
    model.addAttribute("genre", map);
    model.addAttribute("page", page);
    model.addAttribute("keyword", keyword);
    return "/genre/detail";
  }
  
  @PostMapping("/update")
  public String postGenreUpdate(Long no, String name , Model model) {
    Map<String, Object> map = genreService.updateGenreInfo(no , name);
    if((Boolean)map.get("updated")) {
      return "redirect:/genre/list";
    }
    else {
      map.put("status", true);
      model.addAttribute("genre", map);
      return "/genre/detail";
    }
  }

  @PostMapping("/add")
  public String postGenreAdd(String name, Model model) {
    Map<String, Object> map = genreService.addGenreInfo(name);
    if((Boolean)map.get("status")) {
      return "redirect:/genre/list";
    }
    else {
      model.addAttribute("name", name);
      model.addAttribute("result", map);
      return "/genre/add";
    }
  }
  @GetMapping("/delete")
  public String getGenreDelete(@RequestParam Long genre_no) {
    genreService.deleteGenre(genre_no);
    return "redirect:/genre/list";
  }
}
