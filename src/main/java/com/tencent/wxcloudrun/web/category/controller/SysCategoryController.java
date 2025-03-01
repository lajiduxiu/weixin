package com.tencent.wxcloudrun.web.category.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.utils.ResultUtils;
import com.tencent.wxcloudrun.utils.ResultVo;
import com.tencent.wxcloudrun.web.category.entity.ListParm;
import com.tencent.wxcloudrun.web.category.entity.SelectType;
import com.tencent.wxcloudrun.web.category.entity.SysCategory;
import com.tencent.wxcloudrun.web.category.service.SysCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class SysCategoryController {
    @Autowired
    private SysCategoryService sysCategoryService;
    //新增

    @PostMapping
    public ResultVo add(@RequestBody SysCategory sysCategory) {
        if(sysCategoryService.save(sysCategory)){
            return ResultUtils.success("新增成功");
        }
        return ResultUtils.error("新增失败");
    }

    @PutMapping
    public ResultVo edit(@RequestBody SysCategory sysCategory) {
        if(sysCategoryService.updateById(sysCategory)){
            return ResultUtils.success("编辑成功");
        }
        return ResultUtils.error("编辑失败");
    }

    @DeleteMapping("/{categoryId}")
    public ResultVo delete(@PathVariable("categoryId") Long categoryId) {
        if(sysCategoryService.removeById(categoryId)){
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    @GetMapping("/list")
    public ResultVo list(ListParm listParm) {
        IPage<SysCategory> page = new Page<>(listParm.getCurrentPage(), listParm.getPageSize());
        QueryWrapper<SysCategory> query = new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(listParm.getCategoryName()), SysCategory::getCategoryName, listParm.getCategoryName()).orderByAsc(SysCategory::getOrderNum);
        IPage<SysCategory> categoryPage = sysCategoryService.page(page, query);
        return ResultUtils.success("查询成功", categoryPage);
    }
    @GetMapping("/getSelectList")
    public ResultVo getSelectList() {
        List<SysCategory> list = sysCategoryService.list();
//        组装为前端下拉选择器需要的数据格式
        List<SelectType> selectList = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>()).forEach(item -> {
            SelectType type = new SelectType();
                    type.setLabel(item.getCategoryName());
                    type.setValue(Long.valueOf(item.getCategoryId()));
            selectList.add(type);
                });
        return ResultUtils.success("查询成功", selectList);
    }

}
