package com.doyouee.flo_service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Sort;

import com.doyouee.flo_service.service.AdminService;
import com.doyouee.flo_service.vo.AdminAddVO;
import com.doyouee.flo_service.vo.AdminInfoVO;
import com.doyouee.flo_service.vo.AdminLoginVO;
import com.doyouee.flo_service.vo.AdminUpdateVO;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Value("${flo.file.adminImg}") String adminImgPath; // 이미지파일 받아오기 위한 PATH지정

    @Autowired AdminService adminService;

    @PostMapping("/login")
    public String postAdminLogin(AdminLoginVO login, HttpSession session, Model model) {
        //session.setAttribute("login", adminService.loginAdmin(login)); // 바로 내보내면 status에 따른 로그인 처리 화면이 달라지는걸 구현 못하니까 map 만들어서 조건처리
        
        Map<String, Object> resultMap = adminService.loginAdmin(login);
        if((boolean)resultMap.get("status")) {
            session.setAttribute("loginUser", resultMap.get("login"));
            return "redirect:/main";
        }
        else {
            model.addAttribute("message", resultMap.get("message"));
            return "/index";
        }
    }

    @GetMapping("/logout")
    public String getAdminLogout(HttpSession session) {
        session.setAttribute("update_result", null);
        AdminInfoVO admin = (AdminInfoVO)session.getAttribute("loginUser");
        
        // 로그인상태가 아니라면 로그인 페이지로 반환
        if(admin == null){
            return "redirect:/";
        }
        // 로그인 상태이지만 마스터계정(99)이 아니라면 메인페이지로 반환
        else if(admin.getAdmin_grade() != 99){
            return "redirect:/main";
        }

        session.invalidate();
        return "redirect:/";
    }


    @GetMapping("/add")
    public String getAddAdmin() {
        return "/admin/add";
    }


    @PostMapping("/add")
    public String postAddAdmin(AdminAddVO data, Model model /*, @RequestPart MultipartFile adminImg*/ ) { //adminImg가 따로 받아지는 걸로 지정 되었으니 VO에 adminImg 필요없음.
        // System.out.println(data);
        // // System.out.println(adminImg.getOriginalFilename()); //adminImg가 따로 받아지는 걸로 지정 되었으니 VO에 adminImg 필요없음.
        // System.out.println(adminImgPath);

        Map<String, Object> map = adminService.addAdmin(data);
        if((boolean)map.get("status")) {
            return "redirect:/";
        }
        model.addAttribute("inputdata", data); // 아이디 중복검사시 실패하면 데이터 다 날아가는걸 막기위해서 inputdata에 담아서 저장
        model.addAttribute("message", map.get("message"));
        return "/admin/add";
    }


    @GetMapping("/list")
    public String getAdminList(Model model, @RequestParam @Nullable String keyword, @PageableDefault
     (size=10, sort="adminSeq", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session ) 
    {
        // model.addAttribute("list", adminService.getAdminList());
        session.setAttribute("update_result", null);
        AdminInfoVO admin = (AdminInfoVO)session.getAttribute("loginUser");
        
        // 로그인상태가 아니라면 로그인 페이지로 반환
        if(admin == null){
            return "redirect:/";
        }
        // 로그인 상태이지만 마스터계정(99)이 아니라면 메인페이지로 반환
        else if(admin.getAdmin_grade() != 99){
            return "redirect:/main";
        }

        if(keyword == null) keyword = "";
        model.addAttribute("result", adminService.getAdminList(keyword, pageable));
        model.addAttribute("keyword", keyword);
        return "/admin/list";
    }
    
    @GetMapping("/update/status")
    public String getAdminUpdateStatus(@RequestParam Integer value, @RequestParam Long admin_no,
    @RequestParam Integer page, @RequestParam @Nullable String keyword
    )
    {
        adminService.updateAdminStatus(value, admin_no);
        String returnValue = "";
        if(keyword == null || keyword.equals("")) returnValue = "redirect:/admin/list?page="+page;
        else returnValue = "redirect:/admin/list?page="+page+"&keyword="+keyword;
        return returnValue;
    }

    @GetMapping("/delete")
    // keyword => 검색어 
    public String getAdminDelete(@RequestParam Integer value, @RequestParam Long admin_no,
    @RequestParam Integer page, @RequestParam @Nullable String keyword){
        adminService.deleteAdmin(admin_no);
        String returnValue = "";
        // keyword(검색어)가 없거나 
        if(keyword == null || keyword.equals("")) returnValue = "redirect:/admin/list?page="+page;
        else returnValue = "redirect:/admin/list?page="+page+"&keyword="+keyword;   
        return returnValue; 
    }

    @GetMapping("/detail")
    public String getAdminDetail(@RequestParam Long admin_no, Model model, HttpSession session){
        session.setAttribute("update_result", null);
        AdminInfoVO admin = (AdminInfoVO)session.getAttribute("loginUser");
        
        // 로그인상태가 아니라면 로그인 페이지로 반환
        if(admin == null){
            return "redirect:/";
        }
        // 로그인 상태이지만 마스터계정(99)이 아니라면 메인페이지로 반환
        else if(admin.getAdmin_grade() != 99){
            return "redirect:/main";
        }

        model.addAttribute("admin_detail", adminService.getAdminInfo(admin_no));
        // model.addAttribute("admin_detail", adminService.getAdminInfo(admin_no));
        return "/admin/detail";
    }

    @PostMapping("/update")
    public String postadminUpdate(AdminUpdateVO data, HttpSession session){
        System.out.println(data);
        Map<String, Object> map = adminService.updateAdminInfo(data);
        session.setAttribute("update_result", null);
        AdminInfoVO admin = (AdminInfoVO)session.getAttribute("loginUser");
        
        // 로그인상태가 아니라면 로그인 페이지로 반환
        if(admin == null){
            return "redirect:/";
        }
        // 로그인 상태이지만 마스터계정(99)이 아니라면 메인페이지로 반환
        else if(admin.getAdmin_grade() != 99){
            return "redirect:/main";
        }

        if((boolean)map.get("status")){
            return "redirect:/admin/list";
        }
        session.setAttribute("update_result", map);
        return "redirect:/admin/detail?admin_no="+data.getSeq();
        
    }

}
