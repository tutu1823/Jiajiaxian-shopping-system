package com.tyl.model;

import lombok.Data;

@Data
public abstract class BaseQuery {
    private Integer pageSize;//一页多少条信息
    private Integer pageNum;//第几页
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
