package com.tencent.wxcloudrun.web.banner.entity;

import lombok.Data;

@Data
public class BannerParm {
    private Integer currentPage;
    private Integer pageSize;
    private String title;
}
