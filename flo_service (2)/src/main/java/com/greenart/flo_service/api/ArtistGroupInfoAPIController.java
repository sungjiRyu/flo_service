package com.greenart.flo_service.api;

import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.greenart.flo_service.service.ArtistGroupInfoService;
import com.greenart.flo_service.vo.ArtistGroupInfoInsertVO;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/artist/group")
@RequiredArgsConstructor

public class ArtistGroupInfoAPIController {

    private final ArtistGroupInfoService agiService;

    @PutMapping("/insert")
    public ResponseEntity<Object> putArtistGroupInfo(
        ArtistGroupInfoInsertVO data, MultipartFile img)
        {
        Map<String, Object> map = agiService.addArtistGroupInfo(data, img);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
}
