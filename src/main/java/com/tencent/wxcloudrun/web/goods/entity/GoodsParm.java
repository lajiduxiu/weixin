package com.tencent.wxcloudrun.web.goods.entity;

import lombok.Data;

@Data
public class GoodsParm {
    private Integer goodsId;
    private Integer categoryId;
    private String goodsName;
    private String goodsImage;
    private String goodsDesc;
    private String status;
    private String goodsUnit;

    private Integer orderNum;
    private Integer goodsPrice;
}
