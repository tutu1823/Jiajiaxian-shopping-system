package com.tyl.dao;

import com.tyl.model.TuKu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper    //告诉mybatis帮我们做事
public interface TukuDao {

    @Insert(" insert a_tuku (name,img) value (#{name},#{img})")//图表里列名，后是对象名//增
    int create(TuKu tuKu);

    @Delete(" delete from a_tuku where id=#{id} ")
    int delete(Integer id);

    @Update(" update a_tuku set name = #{name},img = #{img} where id=#{id} ")
    int update(TuKu tuKu);

    @Select(" select  *  from a_tuku ")
    List<TuKu> select();

}

