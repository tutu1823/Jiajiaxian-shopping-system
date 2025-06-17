package com.tyl.model;

import lombok.Data;

@Data
public class Category {
    private  Integer id;
    private String name;
    private String img;
    private Integer status;
    private Integer parentId;
    private Integer seq;
}



