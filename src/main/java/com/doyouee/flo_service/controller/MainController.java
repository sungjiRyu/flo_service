package com.doyouee.flo_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    @GetMapping("/")
    public String getIndex(HttpSession session) { // 자바코드방식으로 로그인 시 index로 못가고 main.html로 강제 제어 (근데 뒤로가기는 가능 -> main.html head 설정해줌)
        if(session.getAttribute("loginUser") != null) {
            return "redirect:/main";
        }

        // /templates/views/index.html
        //return "index"; // => 슬래시 안붙으면 상대경로 표시 방법 -> 현재 파일을 열어 둔 곳을 기준으로 그 폴더에 있는 index다. -> 파일 위치 바뀌면 다 바껴야 하니까 헷갈림
        // /templates/views//index.html  -> 내부적으로 //를 /로 바꿈
        return "/index"; // => 슬래시는 절대경로 표시 방법이라는 뜻 ( 시작부터해서 이 파일이 있는 경로로 가는 모든 경로를 다 쓰겠다. ) -> 최상위 경로(루트 : /)에 있는 index다.
    }

    @GetMapping("/main")
    public String getMain() {
        return "/main";
    }
}
