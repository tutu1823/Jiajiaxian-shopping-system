package com.tyl.dao;


import com.tyl.model.Address;
import com.tyl.model.AddressQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressDao {

    @Insert(" INSERT INTO a_address (name, tel, province, city, county, areaCode, addressDetail, isDefault, userId) " +
            " VALUES(#{name}, #{tel}, #{province}, #{city}, #{county}, #{areaCode}, #{addressDetail}, #{isDefault}, #{userId});")
    int insert(Address model);


    @Select(" <script> select *  from a_address where 1 = 1 " +
            " <if test='id != null'>" +
            "  and id = #{id} " +
            " </if>" +
            " <if test='userId != null'>" +
            "  and userId = #{userId} " +
            " </if>" +
            " <if test='isDefault != null'>" +
            "  and isDefault = #{isDefault} " +
            " </if>" +
            " </script>")
    List<Address> select(AddressQuery query);

    @Update(" update  a_address set  isDefault = 0   where userId = #{userId} ")
    Integer updateNotDfaultAddress(Integer userId);

    @Update(" update a_address set isDefault = 1 where id = #{addressId} ")
    Integer updateDefaultAddress(Integer addressId);

    @Update(" update a_address set  name=#{name}, tel=#{tel}, province=#{province}, city=#{city}, county=#{county}, areaCode=#{areaCode}, addressDetail=#{addressDetail}, isDefault=#{isDefault} where id = #{id} ")
    int update(Address address);

    @Delete(" delete from a_address where id = #{id}")
    int delete(Integer id);

}
