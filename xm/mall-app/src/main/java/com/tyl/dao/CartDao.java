package com.tyl.dao;


import com.tyl.model.Cart;
import com.tyl.model.CartQuery;
import com.tyl.model.Product;
import com.tyl.model.ProductQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartDao {


    @Insert(" insert a_cart (productId,productName,productImg,productDesc,price,qty,userId) " +
            " values (#{productId},#{productName},#{productImg},#{productDesc},#{price},#{qty},#{userId}) ")
    int insert(Cart model);


    @Select(" <script> " +
            " select *  from a_cart where 1=1 " +
            " <if test='ids != null'>" +
            "   and id in " +
            " <foreach collection='ids' item='it' open='(' close=')' separator=',' > " +
            " #{it} " +
            " </foreach> " +
            " </if> " +
            " <if test='productId != null'>" +
            "   and productId = #{productId} " +
            " </if> " +
            " <if test='userId != null'>" +
            "  and  userId = #{userId}" +
            " </if> " +
            "</script> ")
    List<Cart> select(CartQuery query);

    @Update(" update a_cart set qty = #{qty} where id = #{id}")
    int updateQty(Integer id, Integer qty);

    @Delete(" delete from  a_cart where id = #{id}")
    int delete(Integer id);

    // delete from a_cart where id in (1,2,3)
    //delete from a_cart where id in (1,2,3)
    @Delete(" <script> delete from a_cart where id in " +
            " <foreach collection='ids' item='it' open='(' close=')' separator=','>  " +
            "  #{it}" +
            " </foreach>" +
            " </script>")
    int deleteByIds(List<Integer> ids);

}