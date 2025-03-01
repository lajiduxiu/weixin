package com.tencent.wxcloudrun.web.user_order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.web.user_order.entity.OrderParm;
import com.tencent.wxcloudrun.web.user_order.entity.ParmDetail;
import com.tencent.wxcloudrun.web.user_order.entity.UserOrder;
import com.tencent.wxcloudrun.web.user_order.entity.WxOrderParm;
import com.tencent.wxcloudrun.web.user_order.mapper.UserOrderMapper;
import com.tencent.wxcloudrun.web.user_order.service.UserOrderService;
import com.tencent.wxcloudrun.web.user_order_detail.entity.UserOrderDetail;
import com.tencent.wxcloudrun.web.user_order_detail.service.impl.UserOrderDetailServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author 陈
* @description 针对表【user_order】的数据库操作Service实现
* @createDate 2024-07-24 21:43:17
*/
@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder>
    implements UserOrderService{
    @Autowired
    private UserOrderDetailServiceImpl userOrderDetailService;

    @Override
    public void splaceOrder(OrderParm parm) {
        //操作订单主表
        UserOrder order = new UserOrder();
        BeanUtils.copyProperties(parm, order);
        order.setStatus("0");
        order.setCreateTime(new Date());
        int ls = this.baseMapper.insert(order);
        //操作子表:订单对应的商品
        if(ls > 0){
            List<ParmDetail> details = parm.getDetails();
            List<UserOrderDetail> list = new ArrayList<>();
            if(!details.isEmpty()){
                for (ParmDetail detail : details){
                    UserOrderDetail detailEntity = new UserOrderDetail();
                    BeanUtils.copyProperties(detail, detailEntity);
                    //设置关联的主表
                    detailEntity.setOrderId(order.getOrderId());
                    list.add(detailEntity);
                }
            }
            //批量插入订单详情表
            userOrderDetailService.saveBatch(list);
        }

    }

    @Override
    public IPage<UserOrder> getOrderList(WxOrderParm parm) {
        //查询条件
        QueryWrapper<UserOrder> query = new QueryWrapper<>();
        query.lambda().eq(UserOrder::getOpenid, parm.getOpenid())
                .eq(StringUtils.isNotEmpty(parm.getType()),UserOrder::getStatus, parm.getType()).orderByDesc(UserOrder::getCreateTime);
        //构造分页对象
        IPage<UserOrder> page = new Page<>(parm.getCurrentPage(), parm.getPageSize());
        //查询订单主表
        IPage<UserOrder> order = this.baseMapper.selectPage(page, query);
        //查询子表
        if(!order.getRecords().isEmpty()){
            for(UserOrder o : order.getRecords()){
                QueryWrapper<UserOrderDetail> detailQuery = new QueryWrapper<>();
                detailQuery.lambda().eq(UserOrderDetail::getOrderId, o.getOrderId());
                List<UserOrderDetail> details = userOrderDetailService.list(detailQuery);
                //设置订单对应的商品
                o.setGoodsList(details);
            }
        }
        return order;
    }

    @Override
    public IPage<UserOrder> getPcOrderList(WxOrderParm parm) {
        //查询条件
        QueryWrapper<UserOrder> query = new QueryWrapper<>();
        query.lambda().eq(StringUtils.isNotEmpty(parm.getType()),UserOrder::getStatus, parm.getType()).like(StringUtils.isNotEmpty(parm.getUserName()),UserOrder::getUserName, parm.getUserName()).orderByDesc(UserOrder::getCreateTime);
        //构造分页对象
        IPage<UserOrder> page = new Page<>(parm.getCurrentPage(), parm.getPageSize());
        //查询订单主表
        IPage<UserOrder> order = this.baseMapper.selectPage(page, query);
        //查询子表
        if(!order.getRecords().isEmpty()){
            for(UserOrder o : order.getRecords()){
                QueryWrapper<UserOrderDetail> detailQuery = new QueryWrapper<>();
                detailQuery.lambda().eq(UserOrderDetail::getOrderId, o.getOrderId());
                List<UserOrderDetail> details = userOrderDetailService.list(detailQuery);
                //设置订单对应的商品
                o.setGoodsList(details);
            }
        }
        return order;
    }
}




