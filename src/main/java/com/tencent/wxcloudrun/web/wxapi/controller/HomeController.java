package com.tencent.wxcloudrun.web.wxapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.utils.ResultUtils;
import com.tencent.wxcloudrun.utils.ResultVo;
import com.tencent.wxcloudrun.web.banner.entity.SysBanner;
import com.tencent.wxcloudrun.web.banner.service.SysBannerService;
import com.tencent.wxcloudrun.web.goods.entity.SysGoods;
import com.tencent.wxcloudrun.web.goods.service.SysGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @Autowired
    private SysBannerService sysBannerService;

    @Autowired
    private SysGoodsService sysGoodsService;


    //首页轮播图
    @GetMapping("/getSwipperList")
    public ResultVo getSwiperList() {
        QueryWrapper<SysBanner> query = new QueryWrapper<>();
        query.lambda().eq(SysBanner::getStatus, "1");
        List<SysBanner> bannerList = sysBannerService.list(query);
        return ResultUtils.success("查询成功",bannerList);
    }

    //首页热推
    @GetMapping("/getHotList")
    public ResultVo getHotList() {
        QueryWrapper<SysGoods> query = new QueryWrapper<>();
        query.lambda().eq(SysGoods::getStatus, "1").orderByAsc(SysGoods::getOrderNum);
        List<SysGoods> list = sysGoodsService.list(query);
        return ResultUtils.success("查询成功",list);
    }
}
