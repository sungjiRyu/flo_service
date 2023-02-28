package com.greenart.flo.api;

import com.greenart.flo.service.AdminSecurityService;
import com.greenart.flo.vo.AdminInfoVO;
import com.greenart.flo.vo.LoginVO;
import com.greenart.flo.vo.admin.response.AdminLoginResponseVO;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor

public class AdminAPIController {
    private final AdminSecurityService adminSecurityService;
    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponseVO> postAdminLogin(@RequestBody LoginVO login) {
        AdminLoginResponseVO response = adminSecurityService.login(login);
        return new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("/details/{id}")
    public ResponseEntity<AdminInfoVO> getAdminDetailInfo(@PathVariable String id){
        return new ResponseEntity<>(adminSecurityService.getAdminDetailInfo(id), HttpStatus.OK);
    
}
}