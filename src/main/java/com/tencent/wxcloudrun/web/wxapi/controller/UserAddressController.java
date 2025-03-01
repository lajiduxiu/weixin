package com.tencent.wxcloudrun.web.wxapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.tencent.wxcloudrun.utils.ResultUtils;
import com.tencent.wxcloudrun.utils.ResultVo;
import com.tencent.wxcloudrun.web.address.entity.UserAddress;
import com.tencent.wxcloudrun.web.address.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/wxapi/address")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    //新增地址
    @PostMapping
    public ResultVo add(@RequestBody UserAddress userAddress) {
        if (userAddressService.save(userAddress)) {
            return ResultUtils.success("新增地址成功");
        }
        return ResultUtils.error("新增地址失败");
    }
    //修改地址
    @PutMapping
    public ResultVo edit(@RequestBody UserAddress userAddress) {
        //如果这个地址是默认，就把其他默认地址的status改为0
        if(Objects.equals(userAddress.getStatus(), "1")){
            LambdaUpdateWrapper<UserAddress> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(UserAddress::getOpenid, userAddress.getOpenid()).eq(UserAddress::getStatus, "1").set(UserAddress::getStatus, "0");
            userAddressService.update(wrapper);
        }
        if (userAddressService.updateById(userAddress)) {
            return ResultUtils.success("编辑地址成功");
        }
        return ResultUtils.error("编辑地址失败");
    }

    //列表
    @GetMapping("/list")
    public ResultVo list(String openid) {
        //排序：按status排序，默认的排在第一条
        QueryWrapper<UserAddress> query = new QueryWrapper<>();
        query.lambda().eq(UserAddress::getOpenid, openid).orderByDesc(UserAddress::getStatus);
        List<UserAddress> list = userAddressService.list(query);
        return ResultUtils.success("查询成功", list);
    }

    //查询默认地址
    @GetMapping("/getAddress")
    public ResultVo getAddress(String openid) {
        QueryWrapper<UserAddress> query = new QueryWrapper<>();
        query.lambda().eq(UserAddress::getOpenid, openid).eq(UserAddress::getStatus, "1");
        UserAddress one = userAddressService.getOne(query);
        return ResultUtils.success("查询成功", one);
    }
}
