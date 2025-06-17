package com.tyl.enums;

import java.util.Arrays;
import java.util.Optional;

public enum OrderStatus {

    ORDERINGOK(0,"下单成功","待支付"),
    PAYOK(10,"支付成功","配送中"),
    DELIVER(20,"配送中",""),
    COMPLETE(30,"订单完成",""),
    CANCEL(120,"订单取消","");

    private  Integer code;
    private String msg;
    private String customerMsg;

    OrderStatus(Integer code, String msg,String customerMsg){
        this.code = code;
        this.msg = msg;
        this.customerMsg = customerMsg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getCustomerMsg() {
        return customerMsg;
    }


    public  static  OrderStatus findByCode(Integer code){
        Optional<OrderStatus> first = Arrays.stream(OrderStatus.values()).filter(p -> p.getCode().equals(code)).findFirst();
        return first.orElse(null);
    }



}
