package com.tyl.model;

import lombok.Data;

@Data
public class OrderMasterQuery {

    private Long[] ids;
    private Long id;
    private Integer userId;
    private Integer status ;

}
