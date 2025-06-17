package com.tyl.model;

import lombok.Data;

@Data
public class ProductQuery extends BaseQuery {
    //只要是继承了ProductQuery就有其两个属性
    private  Integer id;
    private String name;
    private Integer categoryId;

}




