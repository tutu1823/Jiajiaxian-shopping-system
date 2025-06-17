package com.tyl.model;

import lombok.Data;

@Data
public class CategoryQuery {
    private  Integer id;
    private String name;
    private Integer parentId;
}



