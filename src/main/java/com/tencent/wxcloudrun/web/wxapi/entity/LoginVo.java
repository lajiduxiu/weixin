package com.tencent.wxcloudrun.web.wxapi.entity;

import lombok.Data;

@Data
public class LoginVo {
    private String openId;
    private String sessionKey;
}
