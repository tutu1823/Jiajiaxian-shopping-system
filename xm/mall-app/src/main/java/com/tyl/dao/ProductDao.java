package com.tyl.dao;

import com.tyl.model.Category;
import com.tyl.model.CategoryQuery;
import com.tyl.model.Product;
import com.tyl.model.ProductQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProductDao {

    @Select(" <script> select *  from a_product where status = 1 " +
            "<if test='id != null'>" +
            " and id = #{id} " +
            "</if>" +
            "<if test='categoryId != null'>" +
            " and categoryId = #{categoryId} " +
            "</if>" +
            "<if test='name != null'>" +
            " and name like concat('%',#{name},'%') " +
            "</if> " +
            " order by id " +
            " </script>")
    List<Product> select(ProductQuery query);

    @Insert(" insert a_product (name,img,categoryId,seq,price,brief,subName) value (#{name},#{img},#{categoryId},#{seq},#{price},#{brief},#{subName}) ")
    int insert(Product model);

    @Update(" update a_product set name = #{name},img = #{img},categoryId=#{categoryId},seq=#{seq},price=#{price},brief=#{brief},subName=#{subName} where id = #{id} ")
    int update(Product model);

    //软删除
    @Update(" update a_product set status = 0 where id = #{id} ")
    int delete(Integer  id);
}

