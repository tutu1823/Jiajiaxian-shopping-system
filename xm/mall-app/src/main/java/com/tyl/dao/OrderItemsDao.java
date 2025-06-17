package com.tyl.dao;


import com.tyl.model.*;
import com.tyl.model.AddressQuery;
import com.tyl.model.OrderItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderItemsDao {

    @Insert(" INSERT INTO a_order_items (productId, productName, productImg, qty, price, orderId) " +
            " VALUES(#{productId},#{productName},#{productImg},#{qty},#{price},#{orderId});")
    int insert(OrderItem model);


    @Select(" <script> select *  from a_order_items where 1 = 1 " +
            " <if test='id != null'>" +
            "  and id = #{id} " +
            " </if>" +
            " <if test='orderId != null'>" +
            "  and orderId = #{orderId} " +
            " </if>" +
            " </script>")
    List<OrderItem> select(OrderItemQuery query);



}
