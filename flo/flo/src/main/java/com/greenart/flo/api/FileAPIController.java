package com.greenart.flo.api;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.flo.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class FileAPIController {
    private final FileService fileService;

    @GetMapping("/{type}/{filename}")
    public ResponseEntity<Resource> getImageFile(
        @PathVariable String type, @PathVariable String filename
    ) throws Exception {
        return fileService.getImageFile(type, filename);
    }
}
