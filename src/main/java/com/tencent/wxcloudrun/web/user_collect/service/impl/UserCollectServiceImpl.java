package com.tencent.wxcloudrun.web.user_collect.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.web.user_collect.entity.UserCollect;
import com.tencent.wxcloudrun.web.user_collect.mapper.UserCollectMapper;
import com.tencent.wxcloudrun.web.user_collect.service.UserCollectService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈
* @description 针对表【user_collect】的数据库操作Service实现
* @createDate 2024-07-31 19:51:38
*/
@Service
public class UserCollectServiceImpl extends ServiceImpl<UserCollectMapper, UserCollect>
    implements UserCollectService{

    @Override
    public List<UserCollect> getList(String openid) {
        return this.baseMapper.getList(openid);
    }
}




