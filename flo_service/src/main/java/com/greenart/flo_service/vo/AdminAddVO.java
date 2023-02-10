package com.greenart.flo_service.vo;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.Data;

@Data
public class AdminAddVO {
  private String id;
  private String pwd;
  private String name;
  private Integer type;
  // private String adminImg;
}
