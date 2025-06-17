package com.tyl.controller;


import cn.hutool.core.util.ObjectUtil;
import com.tyl.dao.AddressDao;
import com.tyl.model.Address;
import com.tyl.model.AddressQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressApi {

    @Autowired
    private AddressDao addressDao;

    @PostMapping
    public Integer insert(@RequestBody Address address) {
        //省略校验...

        //如果当前新增的地址是默认地址
        //判断目前这个用户数据库里面有没有默认的收货地址
        //如果有 设置成0
        //如果没有，正常插入
        if(address.getIsDefault().equals(true)){
            AddressQuery query  = new AddressQuery();
            query.setUserId(address.getUserId());
            query.setIsDefault(true);
            List<Address> addressList = addressDao.select(query);
            if(ObjectUtil.isNotEmpty(addressList)){
                addressDao.updateNotDfaultAddress(address.getUserId());
            }

        }

        return  addressDao.insert(address);

    }


    @PutMapping
    public Integer update(@RequestBody Address address) {

        if(address.getIsDefault().equals(true)){
            AddressQuery query  = new AddressQuery();
            query.setUserId(address.getUserId());
            query.setIsDefault(true);
            List<Address> addressList = addressDao.select(query);
            if(ObjectUtil.isNotEmpty(addressList)){
                addressDao.updateNotDfaultAddress(address.getUserId());
            }
        }
        return  addressDao.update(address);

    }

    @DeleteMapping
    public Integer delete(@RequestParam Integer id){
        return  addressDao.delete(id);
    }

    @GetMapping
    public List<Address> select(AddressQuery query){
        return  addressDao.select(query);
    }


    @GetMapping("/update/default") //?userId=5&addressId=12
    public Integer updateDefaultAddress(@RequestParam Integer userId, @RequestParam Integer addressId){

        addressDao.updateNotDfaultAddress(userId);
        return  addressDao.updateDefaultAddress(addressId);

    }



}
