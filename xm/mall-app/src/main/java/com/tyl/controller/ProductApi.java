package com.tyl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyl.dao.CategoryDao;
import com.tyl.dao.ProductDao;
import com.tyl.model.Category;
import com.tyl.model.CategoryQuery;
import com.tyl.model.Product;
import com.tyl.model.ProductQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductApi {

    @Autowired
    private ProductDao productDao;

    @GetMapping
    public PageInfo<Product> select(ProductQuery query){

        if(query.getPageNum() != null && query.getPageSize() !=null){
            PageHelper.startPage(query.getPageNum(),query.getPageSize());
        }

        List<Product> products =    productDao.select(query);

        PageInfo<Product> productPageInfo = new PageInfo<>(products);

        return productPageInfo;
    }

    @PostMapping
    public Integer create(@RequestBody Product model){
        return productDao.insert(model);
    }
    @PutMapping
    public Integer update(@RequestBody Product model){
        return productDao.update(model);
    }
    //删除对象
    @DeleteMapping
    public Integer delete(@RequestParam Integer id){
        return productDao.delete(id);
    }
}
