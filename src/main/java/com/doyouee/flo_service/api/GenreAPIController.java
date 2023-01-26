package com.doyouee.flo_service.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doyouee.flo_service.service.GenreService;

import jakarta.annotation.Nullable;

@RestController
@RequestMapping("/api/genre")
public class GenreAPIController {
    @Autowired GenreService genreService;

    @GetMapping("/list")
    public ResponseEntity<Object> getGenreList(@RequestParam @Nullable String keyword, @PageableDefault(size=10, sort="genreSeq", direction = Sort.Direction.DESC) 
        Pageable pageable)
        {
            if(keyword == null) keyword = "";
         
            return new ResponseEntity<>(genreService.getGenreList(keyword, pageable), HttpStatus.OK);
    }

//  @GetMapping("/add")
//     public ResponseEntity<Object> getGenreAdd(String name){
//         Map<String, Object> map = genreService.addGenreInfo(name);
//         return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
//     }

    @GetMapping("/detail")
    public ResponseEntity<Object> getGenreDetail(@RequestParam Long genre_no, @RequestParam @Nullable Integer page, @RequestParam @Nullable String keyword){
        if(page == null) page = 0;
        if(keyword == null) keyword = "";
        Map<String,Object> map = genreService.selectGenreInfo(genre_no);
        map.put("message", null);

        if((Boolean)map.get("status")){
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else{
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> postGenreUpdate(@RequestParam Long no, @RequestParam String name){
        Map<String, Object>map = genreService.updateGenreInfo(no, name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PutMapping("/add")
    public ResponseEntity<Object> postGenreAdd(@RequestParam String name, @RequestParam Model model){
    Map<String, Object>map = genreService.addGenreInfo(name);
    return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> getGenreDelete(@RequestParam Long genre_no){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        genreService.deleteGenre(genre_no);
        map.put("message", "장르 정보를 삭제했습니다.");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    
}
