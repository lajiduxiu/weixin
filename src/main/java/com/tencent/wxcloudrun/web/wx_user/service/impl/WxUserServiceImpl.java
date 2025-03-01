package com.tencent.wxcloudrun.web.wx_user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.web.wx_user.entity.WxUser;
import com.tencent.wxcloudrun.web.wx_user.mapper.WxUserMapper;
import com.tencent.wxcloudrun.web.wx_user.service.WxUserService;
import org.springframework.stereotype.Service;

/**
* @author 陈
* @description 针对表【wx_user】的数据库操作Service实现
* @createDate 2024-07-28 18:42:00
*/
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser>
    implements WxUserService{

    @Override
    public int saveOrUpdateInfo(WxUser wxUser) {
        return this.baseMapper.saveOrUpdateInfo(wxUser);
    }
}




