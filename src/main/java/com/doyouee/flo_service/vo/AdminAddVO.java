package com.doyouee.flo_service.vo;

import lombok.Data;

@Data
public class AdminAddVO {
    private String id;
    private String pwd;
    private String name;
    private Integer type;
    // private String adminImg; // AdminController 에서 @RequestPart MultipartFile adminImg 형식으로 adminImg를 따로 받아지는 걸로 지정했기에 지움
}
