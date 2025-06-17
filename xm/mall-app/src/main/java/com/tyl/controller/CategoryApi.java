package com.tyl.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.tyl.dao.CategoryDao;
import com.tyl.dao.TukuDao;
import com.tyl.model.Category;
import com.tyl.model.CategoryQuery;
import com.tyl.model.TuKu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryApi {

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping
    public List<Category>  select(CategoryQuery query){
        return   categoryDao.select(query);
    }

    @PostMapping
    public Integer create(@RequestBody Category category){
        return categoryDao.create(category);
    }
    @PutMapping
    public Integer update(@RequestBody Category category){
        return categoryDao.update(category);
    }
    //删除对象  DELETE  /api/category?id= 4
    @DeleteMapping
    public Integer delete(@RequestParam Integer id){
        return categoryDao.delete(id);
    }
}
