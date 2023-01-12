package com.doyouee.flo_service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUpdateVO {
private Long seq;
private String pwd;
private String name;
private Integer status;
private Integer grade;
}
