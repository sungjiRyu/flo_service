package com.greenart.flo_service.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    @GetMapping("/")
    public String getIndex(HttpSession session) {
        if(session.getAttribute("loginUser") != null) {
            return "redirect:/main";
        }
        return "/index"; // 절대경로 표시방법
    }
    @GetMapping("/main")
    public String getMain() {
        return "/main";
    }
}