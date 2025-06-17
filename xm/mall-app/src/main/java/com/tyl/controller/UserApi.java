package com.tyl.controller;


import cn.hutool.core.util.ObjectUtil;
import com.tyl.dao.UserDao;
import com.tyl.model.User;
import com.tyl.model.UserQuery;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    @Autowired
    private UserDao userDao;


    @PostMapping  //http://locahost:8081/api/user
    public String regist(@RequestBody User user) throws Exception {
       //逻辑校验
        String phone = user.getPhone();
        if(phone.length() != 11){
            return "用户手机号不正确";
        }
        String password = user.getPassword();
        if(password.length() < 6 ){
            return "密码最少6位";
        }
        //判断当前手机号是否已经注册过
        UserQuery query = new  UserQuery();
        query.setPhone(phone);
        List<User> users = userDao.select(query);
        if(ObjectUtil.isNotEmpty(users)){
            return  "手机号已经注册";
        }
        Integer ret =   userDao.insert(user);
        if(ret.equals(1)){
            return  "ok";
        }
        return  "注册失败";
    }


    @PostMapping("/login")   //http://locahost:8081/api/user/login
    public User login(@RequestBody UserQuery query){

        List<User> users = userDao.select(query);
        if(ObjectUtil.isNotEmpty(users)){
            User user = users.get(0);
            user.setPassword("");
            return user;
        }
         //代码到这里，证明用户不存在
         User user  = new User();
         user.setId(0);
         return user;
    }

}
