package com.tyl.dao;

import com.tyl.model.Product;
import com.tyl.model.ProductQuery;
import com.tyl.model.User;
import com.tyl.model.UserQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDao {

    @Select(" <script> select *  from a_user where status = 1 " +
            "<if test='phone != null'>" +
            " and phone = #{phone} " +
            "</if>" +
            "<if test='password != null'>" +
            " and password = #{password} " +
            "</if> " +
            " order by id " +
            " </script>")
    List<User> select(UserQuery query);

    @Insert(" insert a_user (phone,password) value (#{phone},#{password}) ")
    int insert(User model);

    @Update(" update a_user set password = #{password} where id = #{id} ")
    int update(Integer id , String password);

    //软删除
    @Update(" update a_user set status = 0 where id = #{id} ")
    int delete(Integer  id);
}
