package com.tencent.wxcloudrun.web.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.utils.ResultUtils;
import com.tencent.wxcloudrun.utils.ResultVo;
import com.tencent.wxcloudrun.web.user.entity.SysUser;
import com.tencent.wxcloudrun.web.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping
    public ResultVo add(@RequestBody SysUser user){
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername, user.getUsername());
        SysUser one = sysUserService.getOne(query);
        System.out.println(one);
        if(one != null){
            return ResultUtils.error("用户名已存在!");
        }
        if(sysUserService.save(user)){
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }
    @PutMapping
    public ResultVo edit(@RequestBody SysUser user){
        if(sysUserService.updateById(user)){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Long userId){
        if(sysUserService.removeById(userId)){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }
    @GetMapping("/list")
    public ResultVo list(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(required = false) String name, @RequestParam(required = false) String phone) {

        IPage<SysUser> page = new Page<>(currentPage, pageSize);
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            query.lambda().like(SysUser::getName, name);
        }
        if (StringUtils.isNotEmpty(phone)) {
            query.lambda().like(SysUser::getPhone, phone);
        }
        query.lambda().orderByAsc(SysUser::getName);

        IPage<SysUser> list = sysUserService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }

}
