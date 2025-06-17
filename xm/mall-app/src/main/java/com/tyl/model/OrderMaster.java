package com.tyl.model;

import com.tyl.enums.OrderStatus;
import com.tyl.model.Address;
import com.tyl.model.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderMaster {

    private Long id;
    private Integer userId;
    private Integer addressId;
    private BigDecimal totalPrice;
    private Integer jd;
    private Integer status = 0;
    private String statusX;

    public String getStatusX() {
        return  OrderStatus.findByCode(this.status).getCustomerMsg();
    }

    private List<OrderItem> items;

    private Address address;

}
