package com.tencent.wxcloudrun.web.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.utils.ResultUtils;
import com.tencent.wxcloudrun.utils.ResultVo;
import com.tencent.wxcloudrun.web.category.entity.SelectType;
import com.tencent.wxcloudrun.web.goods.entity.ListParm;
import com.tencent.wxcloudrun.web.goods.entity.SysGoods;
import com.tencent.wxcloudrun.web.goods.service.SysGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/goods")
public class SysGoodsController {
    @Autowired
    private SysGoodsService sysGoodsService;


    @PostMapping
    public ResultVo add(@RequestBody SysGoods sysGoods) {
        sysGoodsService.saveGoods(sysGoods);
        return ResultUtils.success("新增成功！");
    }
    @PutMapping
    public ResultVo edit(@RequestBody SysGoods sysGoods) {
        sysGoodsService.editGoods(sysGoods);
        return ResultUtils.success("编辑成功！");
    }
    @DeleteMapping("/{goodsId}")
    public ResultVo delete(@PathVariable("goodsId") Integer goodsId) {
        sysGoodsService.deleteGoods(goodsId);
        return ResultUtils.success("删除成功！");
    }
    //列表
    @GetMapping("/list")
    public ResultVo getList(ListParm parm) {
        //构造分页对象
        IPage<SysGoods> page = new Page<>(parm.getCurrentPage(), parm.getPageSize());
        //构造查询条件
        QueryWrapper<SysGoods> query = new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(parm.getGoodsName()),SysGoods::getGoodsName,parm.getGoodsName()).orderByAsc(SysGoods::getOrderNum);
        IPage<SysGoods> result = sysGoodsService.page(page,query);
        return ResultUtils.success("查询成功",result);
    }

    @GetMapping("/getSelectList")
    public ResultVo getSelectList() {
        List<SysGoods> list = sysGoodsService.list();
//        组装为前端下拉选择器需要的数据格式
        List<SelectType> selectList = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>()).forEach(item -> {
            SelectType type = new SelectType();
            type.setLabel(item.getGoodsName());
            type.setValue(Long.valueOf(item.getGoodsId()));
            selectList.add(type);
        });
        return ResultUtils.success("查询成功", selectList);
    }
}
