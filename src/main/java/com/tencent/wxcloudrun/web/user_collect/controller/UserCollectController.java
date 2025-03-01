package com.tencent.wxcloudrun.web.user_collect.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.utils.ResultUtils;
import com.tencent.wxcloudrun.utils.ResultVo;
import com.tencent.wxcloudrun.web.user_collect.entity.UserCollect;
import com.tencent.wxcloudrun.web.user_collect.service.UserCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wxapi/collect")
public class UserCollectController {
    @Autowired
    private UserCollectService userCollectService;

    //添加收藏
    @PostMapping("/addCollect")
    public ResultVo addCollect(@RequestBody UserCollect userCollect) {
        //查询是否收藏
        QueryWrapper<UserCollect> query = new QueryWrapper<>();
        query.lambda().eq(UserCollect::getGoodsId, userCollect.getGoodsId()).eq(UserCollect::getOpenid,
                userCollect.getOpenid());
        UserCollect one = userCollectService.getOne(query);
        if (one != null) {
            return ResultUtils.error("您已经收藏该商品");
        }
        ;
        if (userCollectService.save(userCollect)) {
            return ResultUtils.success("收藏成功");
        }

        return ResultUtils.error("收藏失败");
    }

    //取消收藏
    @PostMapping("/cancelCollect")
    public ResultVo cancelCollect(@RequestBody UserCollect userCollect) {
        //查询是否收藏
        QueryWrapper<UserCollect> query = new QueryWrapper<>();
        query.lambda().eq(UserCollect::getGoodsId, userCollect.getGoodsId()).eq(UserCollect::getOpenid,
                userCollect.getOpenid());
        if (userCollectService.remove(query)) {
            return ResultUtils.success("取消收藏成功！");
        }
        return ResultUtils.error("取消收藏失败");
    }

    //收藏列表
    @GetMapping("/list")
    public ResultVo getList(String openid) {
        List<UserCollect> list = userCollectService.getList(openid);
        return ResultUtils.success("收藏列表查询成功！", list);
    }

    //查询该商品是否被收藏
    @GetMapping("/hasCollect")
    public ResultVo hasCollect(UserCollect userCollect) {
        QueryWrapper<UserCollect> query = new QueryWrapper<>();
        query.lambda().eq(UserCollect::getOpenid, userCollect.getOpenid()).eq(UserCollect::getGoodsId,
                userCollect.getGoodsId());
        UserCollect one = userCollectService.getOne(query);
        if (one != null)
            return ResultUtils.success("查询成功！", '1');
        return ResultUtils.success("查询成功！", '0');
    }
}
