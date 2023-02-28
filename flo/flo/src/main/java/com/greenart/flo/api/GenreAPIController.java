package com.greenart.flo.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springdoc.core.converters.models.PageableAsQueryParam;
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

import com.greenart.flo.service.GenreService;
import com.greenart.flo.vo.GenreListResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "곡 장르정보 관리", description = "장르정보 CRUD API")
@RestController
@RequestMapping("/api/genre")
public class GenreAPIController {
    @Autowired GenreService genreService;

    @Operation(summary = "장르 리스트", description = "등록된 장르정보를 10개 단위로 보여줍니다.")
    @PageableAsQueryParam
    @GetMapping("/list")
    public ResponseEntity<GenreListResponseVO> getGenreList(
        @Parameter(description = "검색어", example = "힙합") @RequestParam @Nullable String keyword, 
        // @Parameter(description = "페이징 처리, URL 파라미터로 요청 (예시) /api/genre/list?page=0")
        @Parameter(hidden = true)
        @PageableDefault(size=10, sort="seq", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        if (keyword==null) keyword="";
        return new ResponseEntity<>(genreService.getGenreList(keyword, pageable), HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<Object> getGenreDetail(@RequestParam Long no, @RequestParam @Nullable Integer page, @RequestParam @Nullable String keyword) {
        if (page==null) page = 0;
        if (keyword==null) keyword="";
        Map<String, Object> map = genreService.selectGenreInfo(no);
        if ((Boolean)map.get("status")) {
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> postGenreUpdate(@RequestParam Long no, @RequestParam String name) {
        Map<String, Object> map = genreService.updateGenreInfo(no, name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PutMapping("/add")
    public ResponseEntity<Object> postGenreAdd(@RequestParam String name) {
        Map<String, Object> map = genreService.addGenreInfo(name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> getGenreDelete(@RequestParam Long no) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        genreService.deleteGenre(no);
        map.put("message", "장르 정보를 삭제했습니다.");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
