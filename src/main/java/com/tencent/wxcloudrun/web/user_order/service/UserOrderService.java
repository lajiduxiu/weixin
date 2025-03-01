package com.tencent.wxcloudrun.web.user_order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.web.user_order.entity.OrderParm;
import com.tencent.wxcloudrun.web.user_order.entity.UserOrder;
import com.tencent.wxcloudrun.web.user_order.entity.WxOrderParm;

/**
* @author 陈
* @description 针对表【user_order】的数据库操作Service
* @createDate 2024-07-24 21:43:17
*/
public interface UserOrderService extends IService<UserOrder> {
    void splaceOrder(OrderParm parm);
    IPage<UserOrder> getOrderList(WxOrderParm parm);
    IPage<UserOrder> getPcOrderList(WxOrderParm parm);
}
