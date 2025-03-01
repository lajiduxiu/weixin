package com.tencent.wxcloudrun.web.wx_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.web.wx_user.entity.WxUser;
import org.apache.ibatis.annotations.Param;

/**
* @author 陈
* @description 针对表【wx_user】的数据库操作Mapper
* @createDate 2024-07-28 18:42:00
* @Entity com.tencent.wxcloudrun.web.wx_user.entity.WxUser
*/
public interface WxUserMapper extends BaseMapper<WxUser> {
    int saveOrUpdateInfo(@Param("user") WxUser user);
}




