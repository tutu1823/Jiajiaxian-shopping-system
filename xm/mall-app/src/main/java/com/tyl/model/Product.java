package com.tyl.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private  Integer id;
    private String name;
    private String img;
    private Integer status;
    private Integer categoryId;
    private Integer seq;
    private String subName;
    private BigDecimal price;
    private String brief;
}



