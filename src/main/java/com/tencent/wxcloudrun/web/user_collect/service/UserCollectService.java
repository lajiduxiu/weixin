package com.tencent.wxcloudrun.web.user_collect.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.web.user_collect.entity.UserCollect;

import java.util.List;

/**
* @author 陈
* @description 针对表【user_collect】的数据库操作Service
* @createDate 2024-07-31 19:51:38
*/
public interface UserCollectService extends IService<UserCollect> {
    List<UserCollect> getList(String openid);
}
