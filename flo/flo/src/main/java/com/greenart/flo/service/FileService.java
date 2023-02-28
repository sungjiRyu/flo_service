package com.greenart.flo.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    public String saveImageFile(String type, MultipartFile img) throws Exception {
        // 파일이 저장될 폴더의 경로를 가져와서
        Path targetLocation = Paths.get("/home/flo/images/"+type);
        // 입력된 파일명을 추가하여, 저장될 위치를 설정하고
        targetLocation = targetLocation.resolve(img.getOriginalFilename());
        // 입력된 파일을 저장될 위치에 복사한다.
        Files.copy(img.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        // 저장된 위치를 문자열로 변환해서 내어준다.
        return img.getOriginalFilename();
    }

    public ResponseEntity<Resource> getImageFile(String type, String filename) throws Exception {
        // Path imgLocation = Paths.get(location).normalize();
        Path imgLocation = Paths.get("/home/flo/images/"+type+"/"+filename).normalize();
        Resource r = new UrlResource(imgLocation.toUri());
        String contentType = "application/octet-stream";
        
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+URLEncoder.encode(r.getFilename(), "UTF-8"))
        .body(r);
    }
}
