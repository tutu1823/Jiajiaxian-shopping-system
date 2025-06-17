package com.tyl.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Cart {

    private Integer id;
    private Integer productId;
    private String productName;
    private String productImg;
    private String productDesc;
    private BigDecimal price;
    private Integer qty;
    private Integer userId;


}
