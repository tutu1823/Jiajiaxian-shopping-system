package com.tyl.controller;

import cn.hutool.core.util.ObjectUtil;
import com.tyl.dao.CartDao;
import com.tyl.model.Cart;
import com.tyl.model.CartQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartApi {

    @Autowired
    private CartDao cartDao;

    @PostMapping
    public Integer insert(@RequestBody Cart model) throws Exception {
        //省略校验逻辑
        if(model.getProductId() <= 0){
            throw  new Exception("商品id不正确");
        }
        if(model.getPrice().doubleValue() < 0){
            throw  new Exception("商品价格不正确");
        }
        // 去购物车查询，当前商品是否已经存在 （productId，userId）

        CartQuery query  = new CartQuery();
        query.setProductId(model.getProductId());
        query.setUserId(model.getUserId());
        List<Cart> carts = cartDao.select(query);
        if(ObjectUtil.isNotEmpty(carts)){
            Cart cart = carts.get(0);
            //只要更新这个商品对应的qty数量即可
            Integer qty0 = cart.getQty();
            Integer qty1 = qty0 + 1;
            return    cartDao.updateQty(cart.getId(),qty1);
        } else {
            return cartDao.insert(model);
        }

    }

    @GetMapping("/update/qty")
    //http://localhost:8081/api/cart/update/qty?id=1&qty=9
    public  Integer updateQty(@RequestParam Integer id,@RequestParam Integer qty){

        return  cartDao.updateQty(id,qty);
    }

    @GetMapping
    public List<Cart> select(CartQuery query){
        return  cartDao.select(query);
    }

    @DeleteMapping
    //delete   http://localhost:8081/api/cart?id=2
    public Integer delete(@RequestParam Integer id){
        return  cartDao.delete(id);
    }

//    @DeleteMapping("/delete/ids")
//    //delete   http://localhost:8081/api/cart/delete/ids?ids=1&ids=3
//    public Integer deleteByIds(@RequestParam Integer[] ids){
//        return  cartDao.deleteByIds(ids);
//    }
}
