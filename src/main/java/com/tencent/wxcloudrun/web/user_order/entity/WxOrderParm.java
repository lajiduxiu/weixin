package com.tencent.wxcloudrun.web.user_order.entity;

import lombok.Data;

@Data
public class WxOrderParm {
    private String openid;
    private String type;
    private Integer currentPage;
    private Integer pageSize;
    private String userName;
}
