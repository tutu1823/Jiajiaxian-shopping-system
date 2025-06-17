package com.tyl.dao;

import com.tyl.model.Category;
import com.tyl.model.CategoryQuery;
import com.tyl.model.TuKu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper    //告诉mybatis帮我们做事
public interface CategoryDao {

    @Select(" <script> select *  from a_category where status = 1 " +
            "<if test='id != null'>" +
            " and id = #{id} " +
            "</if>" +
            "<if test='parentId != null'>" +
            " and parentId = #{parentId} " +
            "</if>" +
            "<if test='name != null'>" +
            " and name like concat('%',#{name},'%') " +
            "</if> " +
            " order by seq desc" +
            " </script>")
    List<Category> select(CategoryQuery query);

    @Insert(" insert a_category (name,img,parentId,seq) value (#{name},#{img},#{parentId},#{seq}) ")
    int create(Category tuKu);

    @Update(" update a_category set name = #{name},img = #{img},parentId=#{parentId},seq=#{seq} where id = #{id} ")
    int update(Category tuKu);

    //软删除
    @Update(" update a_category set status = 0 where id = #{id} ")
    int delete(Integer  id);
}

