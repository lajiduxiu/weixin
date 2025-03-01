package com.tencent.wxcloudrun.web.wxapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.utils.ResultUtils;
import com.tencent.wxcloudrun.utils.ResultVo;
import com.tencent.wxcloudrun.web.category.entity.SysCategory;
import com.tencent.wxcloudrun.web.category.service.SysCategoryService;
import com.tencent.wxcloudrun.web.goods.entity.SysGoods;
import com.tencent.wxcloudrun.web.goods.service.SysGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wxapi/category")
public class WxCategoryController {

    @Autowired
    private SysGoodsService sysGoodsService;
    @Autowired
    private SysCategoryService sysCategoryService;

    //分类数据
    @GetMapping("/getCategoryList")
    public ResultVo getGoodsList() {
        QueryWrapper<SysCategory> query = new QueryWrapper<>();
        query.lambda().orderByAsc(SysCategory::getOrderNum);
        List<SysCategory> list = sysCategoryService.list(query);
        if(list.size() > 0){
            for (SysCategory category : list){
                //查询价格
                QueryWrapper<SysGoods> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(SysGoods::getCategoryId, category.getCategoryId()).orderByAsc(SysGoods::getOrderNum);
                List<SysGoods> goods = sysGoodsService.list(queryWrapper);
                category.setGoods(goods);
            }
        }
        return ResultUtils.success("查询成功",list);
    }
}
