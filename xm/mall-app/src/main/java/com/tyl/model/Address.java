package com.tyl.model;

import lombok.Data;

@Data
public class Address {
    private Integer id;
    private String name;
    private String tel;
    private String province;
    private String city;
    private String county;
    private String areaCode;
    private String addressDetail;
    private Boolean isDefault = false;
    private Integer userId;
}


