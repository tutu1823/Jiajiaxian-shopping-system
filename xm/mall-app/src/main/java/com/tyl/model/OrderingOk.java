package com.tyl.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderingOk {
    private  Long orderId;
    private BigDecimal needPay;
}
