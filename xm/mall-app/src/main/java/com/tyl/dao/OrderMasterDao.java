package com.tyl.dao;


import com.tyl.model.Address;
import com.tyl.model.AddressQuery;
import com.tyl.model.OrderMaster;
import com.tyl.model.OrderMasterQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMasterDao {

    @Insert(" INSERT INTO a_order_master (id, userId, addressId, totalPrice, jd, status) " +
            " VALUES(#{id},#{userId},#{addressId},#{totalPrice},#{jd},#{status});")
    int insert(OrderMaster model);



    @Update(" update  a_order_master set  status = #{status}   where id = #{id} ")
    Integer updateOrderStatus(Integer status,Long id);


    @Select(" <script> " +
            " select *  from a_order_master where 1 = 1 " +
            " <if test='ids != null'>" +
            "   and id in " +
            "  <foreach collection='ids' item='it' open='('  close=')' separator=',' >  " +
            "    #{it} " +
            " </foreach> " +
            " </if> " +
            " <if test='id != null'>" +
            "   and id = #{id} " +
            " </if> " +
            " <if test='userId != null'>" +
            "   and userId = #{userId} " +
            " </if> " +
            " <if test='status != null'>" +
            "  and  status = #{status}" +
            " </if> " +
            "</script> ")
    List<OrderMaster> select(OrderMasterQuery query);

}
