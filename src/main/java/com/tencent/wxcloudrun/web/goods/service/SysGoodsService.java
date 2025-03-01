package com.tencent.wxcloudrun.web.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.web.goods.entity.SysGoods;

/**
* @author 陈
* @description 针对表【sys_goods】的数据库操作Service
* @createDate 2024-06-28 11:11:09
*/
public interface SysGoodsService extends IService<SysGoods> {
    //保存
    boolean saveGoods(SysGoods sysGoods);
    boolean editGoods(SysGoods sysGoods);
    boolean deleteGoods(Integer goodsId);
}
