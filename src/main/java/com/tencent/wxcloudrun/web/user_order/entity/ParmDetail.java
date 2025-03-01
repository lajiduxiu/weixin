package com.tencent.wxcloudrun.web.user_order.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ParmDetail {
    private Integer goodsId;
    private String goodsImage;
    private String goodsName;
    private String goodsUnit;
    private String specsName;
    private Integer num;
    private BigDecimal price;
}
