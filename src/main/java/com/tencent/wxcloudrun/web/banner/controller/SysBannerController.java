package com.tencent.wxcloudrun.web.banner.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.utils.ResultUtils;
import com.tencent.wxcloudrun.utils.ResultVo;
import com.tencent.wxcloudrun.web.banner.entity.BannerParm;
import com.tencent.wxcloudrun.web.banner.entity.SysBanner;
import com.tencent.wxcloudrun.web.banner.service.SysBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/banner")
public class SysBannerController {
    @Autowired
    private SysBannerService sysBannerService;
    //新增
    @PostMapping
    public ResultVo add(@RequestBody SysBanner sysBanner) {
        if(sysBannerService.save(sysBanner)){
            return ResultUtils.success("新增成功");
        }
        return ResultUtils.error("新增失败");
    }
    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody SysBanner sysBanner) {
        if(sysBannerService.updateById(sysBanner)){
            return ResultUtils.success("编辑成功");
        }
        return ResultUtils.error("编辑失败");
    }
    //删除
    @DeleteMapping("/{id}")
    public ResultVo delete(@PathVariable("id") Long  id) {
        if(sysBannerService.removeById(id)){
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }
    //列表
    @GetMapping("/list")
    public ResultVo list(BannerParm listParm) {
        IPage<SysBanner> page = new Page<>(listParm.getCurrentPage(), listParm.getPageSize());
        QueryWrapper<SysBanner> query = new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(listParm.getTitle()), SysBanner::getTitle, listParm.getTitle()).orderByAsc(SysBanner::getOrderNum);
        IPage<SysBanner> list = sysBannerService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }

}
