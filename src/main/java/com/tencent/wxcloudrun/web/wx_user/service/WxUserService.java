package com.tencent.wxcloudrun.web.wx_user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.web.wx_user.entity.WxUser;

/**
* @author 陈
* @description 针对表【wx_user】的数据库操作Service
* @createDate 2024-07-28 18:42:00
*/
public interface WxUserService extends IService<WxUser> {
    int saveOrUpdateInfo(WxUser wxUser);
}
