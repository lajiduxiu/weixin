package com.tencent.wxcloudrun.web.wx_user.controller;

import com.tencent.wxcloudrun.utils.ResultUtils;
import com.tencent.wxcloudrun.utils.ResultVo;
import com.tencent.wxcloudrun.web.wx_user.entity.WxUser;
import com.tencent.wxcloudrun.web.wx_user.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wxapi/wxUser")
public class WxUserController {
    @Autowired
    private WxUserService wxUserService;

    @PostMapping("/saveOrUpdate")
    public ResultVo saveOrUpdate(@RequestBody WxUser wxUser){
        wxUserService.saveOrUpdateInfo(wxUser);
        return ResultUtils.success("更新成功");
    }

    @GetMapping("/getUserInfo")
    private ResultVo getUserInfo(String openid){
        WxUser wxUser = wxUserService.getById(openid);
        return ResultUtils.success("头像昵称查询成功",wxUser);
    }
}
