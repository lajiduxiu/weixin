package com.tencent.wxcloudrun.web.user_collect.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.web.user_collect.entity.UserCollect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 陈
* @description 针对表【user_collect】的数据库操作Mapper
* @createDate 2024-07-31 19:51:38
* @Entity com.tencent.wxcloudrun.web.user_collect.entity.UserCollect
*/
public interface UserCollectMapper extends BaseMapper<UserCollect> {
    List<UserCollect> getList(@Param("openid") String openid);
}




