package com.tencent.wxcloudrun.web.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.web.goods.entity.SysGoods;
import com.tencent.wxcloudrun.web.goods.mapper.SysGoodsMapper;
import com.tencent.wxcloudrun.web.goods.service.SysGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 陈
 * @description 针对表【sys_goods】的数据库操作Service实现
 * @createDate 2024-06-28 11:11:09
 */
@Service
public class SysGoodsServiceImpl extends ServiceImpl<SysGoodsMapper, SysGoods> implements SysGoodsService {

    @Override
    @Transactional
    public boolean saveGoods(SysGoods sysGoods) {
        //1.保存菜品
        return this.baseMapper.insert(sysGoods)>0;
    }

    @Override
    @Transactional
    public boolean editGoods(SysGoods sysGoods) {
        return this.baseMapper.updateById(sysGoods)>0;
    }

    @Override
    public boolean deleteGoods(Integer goodsId) {
        //1.删除菜品
        return this.baseMapper.deleteById(goodsId)>0;
    }
}




