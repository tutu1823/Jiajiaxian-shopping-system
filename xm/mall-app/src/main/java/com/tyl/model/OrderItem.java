package com.tyl.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {

    private Long id;
    private Integer productId;
    private String productName;
    private String productImg;
    private BigDecimal price;
    private Integer qty;
    private Long orderId ;
    private Integer cartId;

}

