package com.tencent.wxcloudrun.web.user.entity;

import lombok.Data;

@Data
public class UserPageParm {
    private Integer currentPage;
    private Integer pageSize;
    private String name;
    private String phone;
}
