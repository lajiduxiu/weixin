package com.tencent.wxcloudrun.web.user_order.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderParm {
    private String openid;
    private String userName;
    private String address;
    private String phone;
    private BigDecimal price;
    private List<ParmDetail> details = new ArrayList<>();
}
