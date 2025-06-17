package com.tyl.model;

import lombok.Data;

@Data
public class CartQuery {


    private Integer[] ids;

    private Integer  productId;

    private Integer userId;


}
