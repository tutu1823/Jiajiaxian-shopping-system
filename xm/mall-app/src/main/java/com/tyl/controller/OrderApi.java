package com.tyl.controller;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.tyl.dao.AddressDao;
import com.tyl.dao.CartDao;
import com.tyl.dao.OrderItemsDao;
import com.tyl.dao.OrderMasterDao;
import com.tyl.enums.OrderStatus;
import com.tyl.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderApi {

    @Autowired
    private OrderMasterDao orderMasterDao;
    @Autowired
    private OrderItemsDao orderItemsDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private CartDao cartDao;

    @GetMapping
    public  List<OrderMaster>  select(OrderMasterQuery query){
        //业务判断...
        List<OrderMaster> orders = orderMasterDao.select(query);
        for (OrderMaster master : orders){
            OrderItemQuery itemQuery = new OrderItemQuery();
            itemQuery.setOrderId(master.getId());
            List<OrderItem> items = orderItemsDao.select(itemQuery);
            master.setItems(items);
            Integer addressId = master.getAddressId();
            AddressQuery addressQuery = new AddressQuery();
            addressQuery.setId(addressId);
            List<Address> addressList = addressDao.select(addressQuery);
            Address address = addressList.get(0);
            master.setAddress(address);
        }
        return orders;
    }

    @GetMapping("/items")
    public List<OrderItem> selectDetail(OrderItemQuery query){
        List<OrderItem> items = orderItemsDao.select(query);
        return items;
    }


    @PostMapping()
    public OrderingOk orderMaster(@RequestBody OrderMaster orderMaster) {
        String format = DateUtil.format(DateUtil.date(), "yyMMddHHmmss");
        Long orderId = Long.valueOf(format);
        orderMaster.setId(orderId);
        //计算totalPrice
        BigDecimal totalPrice = BigDecimal.ZERO;

        List<OrderItem> items = orderMaster.getItems();

        for (OrderItem item : items) {
            item.setOrderId(orderId);
            orderItemsDao.insert(item);
            BigDecimal price = NumberUtil.mul(item.getPrice(), item.getQty());
            totalPrice = NumberUtil.add(totalPrice,price);
        }
        if(orderMaster.getJd() > 0 ){
            double jdPrice = NumberUtil.mul(orderMaster.getJd().floatValue(), 0.01);
            totalPrice  = NumberUtil.sub(totalPrice,jdPrice);
        }
        orderMaster.setTotalPrice(totalPrice);
        OrderingOk  orderingOk  = new OrderingOk();
        orderingOk.setOrderId(orderId);
        orderingOk.setNeedPay(totalPrice);

        if(totalPrice.doubleValue() > 0){
            orderMaster.setStatus(OrderStatus.ORDERINGOK.getCode());//待支付
        } else {

            orderMaster.setStatus(OrderStatus.PAYOK.getCode()); // 支付成功
        }

        orderMasterDao.insert(orderMaster);

        //删除购物车
        List<Integer> cartIds = CollStreamUtil.toList(orderMaster.getItems(), OrderItem::getCartId);
        cartDao.deleteByIds(cartIds);

        return orderingOk;
    }


    @GetMapping("/status") //api/order/status?id=23157686&status=10
    public Integer updateStatus(@RequestParam  Long id,@RequestParam Integer status){
        //业务判断省略...
        return orderMasterDao.updateOrderStatus(status,id);
    }

}
