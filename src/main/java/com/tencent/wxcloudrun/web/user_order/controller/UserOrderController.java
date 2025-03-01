package com.tencent.wxcloudrun.web.user_order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.utils.ResultUtils;
import com.tencent.wxcloudrun.utils.ResultVo;
import com.tencent.wxcloudrun.web.user_order.entity.OrderParm;
import com.tencent.wxcloudrun.web.user_order.entity.SendParm;
import com.tencent.wxcloudrun.web.user_order.entity.UserOrder;
import com.tencent.wxcloudrun.web.user_order.entity.WxOrderParm;
import com.tencent.wxcloudrun.web.user_order.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wxapi/order")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;
    //下单
    @PostMapping("/splaceOrder")
    public ResultVo splaceOrder(@RequestBody OrderParm orderParm) {
        userOrderService.splaceOrder(orderParm);
        return ResultUtils.success("提交成功");
    }

    //查询订单
    @GetMapping("/getOrderList")
    public ResultVo getOrderList(WxOrderParm parm) {
         IPage<UserOrder> list = userOrderService.getOrderList(parm);
        return ResultUtils.success("订单查询成功",list);
    }

    //查询PC订单
    @GetMapping("/getPcOrderList")
    public ResultVo getPcOrderList(WxOrderParm parm) {
        IPage<UserOrder> list = userOrderService.getPcOrderList(parm);
        return ResultUtils.success("订单查询成功",list);
    }

    //发货
    @PostMapping("/sendOrder")
    public ResultVo sendOrder(@RequestBody SendParm parm) {
        //判断订单是否被取消
        QueryWrapper<UserOrder> query = new QueryWrapper<>();
        query.lambda().eq(UserOrder::getOrderId,parm.getOrderId()).eq(UserOrder::getStatus,'3');
        UserOrder one = userOrderService.getOne(query);
        if(one != null){
            return ResultUtils.error("订单已被取消，不能发货!");
        }
        //更新条件
        LambdaUpdateWrapper<UserOrder> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserOrder::getOrderId,parm.getOrderId()).set(UserOrder::getStatus,'1');
        userOrderService.update(wrapper);
        if(userOrderService.update(wrapper)){
            return ResultUtils.success("发货成功!");
        }
        return ResultUtils.success("发货失败!");
    }
    //取消订单
    @PostMapping("/cancelOrder")
    public ResultVo cancelOrder(@RequestBody SendParm parm) {
        //如果已发货，不能取消
        QueryWrapper<UserOrder> query = new QueryWrapper<>();
        query.lambda().eq(UserOrder::getOrderId,parm.getOrderId()).eq(UserOrder::getStatus,'1');
        UserOrder one = userOrderService.getOne(query);
        if(one != null){
            return ResultUtils.error("订单已发货，不能取消!");
        }
        //更新条件
        LambdaUpdateWrapper<UserOrder> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserOrder::getOrderId,parm.getOrderId()).set(UserOrder::getStatus,'3');
        userOrderService.update(wrapper);
        if(userOrderService.update(wrapper)){
            return ResultUtils.success("取消成功!");
        }
        return ResultUtils.success("取消失败!");
    }

    //确定收货
    @PostMapping("/confirmOrder")
    public ResultVo confirmOrder(@RequestBody SendParm parm) {
        //更新条件
        LambdaUpdateWrapper<UserOrder> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserOrder::getOrderId,parm.getOrderId()).set(UserOrder::getStatus,'2');
        userOrderService.update(wrapper);
        if(userOrderService.update(wrapper)){
            return ResultUtils.success("收货成功!");
        }
        return ResultUtils.success("收货失败!");
    }
}
