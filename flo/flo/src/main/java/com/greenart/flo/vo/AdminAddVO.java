package com.greenart.flo.vo;

import lombok.Data;

@Data
public class AdminAddVO {
    private String id;
    private String pwd;
    private String name;
    private Integer type;
    private String adminImg;
}
