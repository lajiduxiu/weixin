package com.tencent.wxcloudrun.web.goods_comment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.utils.ResultUtils;
import com.tencent.wxcloudrun.utils.ResultVo;
import com.tencent.wxcloudrun.web.goods_comment.entity.CommentParm;
import com.tencent.wxcloudrun.web.goods_comment.entity.GoodsComment;
import com.tencent.wxcloudrun.web.goods_comment.service.GoodsCommentService;
import com.tencent.wxcloudrun.web.user_order_detail.entity.UserOrderDetail;
import com.tencent.wxcloudrun.web.user_order_detail.service.UserOrderDetailService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/wxapi/comment")
public class GoodsCommentController {

    @Resource
    private UserOrderDetailService userOrderDetailService;
    @Autowired
    private GoodsCommentService goodsCommentService;
    //新增评论
    @PostMapping("/addComment")
    public ResultVo addComment(@RequestBody GoodsComment goodsComment){
        //根据订单id查询商品
        QueryWrapper<UserOrderDetail> query = new QueryWrapper<>();
        query.lambda().eq(UserOrderDetail::getOrderId, goodsComment.getOrderId());
        List<UserOrderDetail> list = userOrderDetailService.list(query);
        List<GoodsComment> goodsComments = new ArrayList<>();
        if(!list.isEmpty()){
            for (UserOrderDetail userOrderDetail : list){
                GoodsComment comment = new GoodsComment();
                BeanUtils.copyProperties(goodsComment, comment);
                comment.setGoodsId(userOrderDetail.getGoodsId());
                comment.setCreateTime(new Date());
                goodsComments.add(comment);
            }
        }
        //批量插入
        goodsCommentService.saveBatch(goodsComments);
        return ResultUtils.success("评论成功");
    }

    //小程序评论列表
    @GetMapping("/commentList")
    public ResultVo commentList(Integer goodsId){
        List<GoodsComment> list = goodsCommentService.commentList(goodsId);
        return ResultUtils.success("评论查询成功",list);
    }

    //pc评论列表
    @GetMapping("/pcCommentList")
    public ResultVo pcCommentList(CommentParm parm){
        IPage<GoodsComment> list = goodsCommentService.getList(parm);
        return ResultUtils.success("pc评论查询成功",list);
    }

    //pc删除评论
    @DeleteMapping("/{commentId}")
    public ResultVo delete(@PathVariable("commentId") Integer commentId) {
        goodsCommentService.removeById(commentId);
        return ResultUtils.success("删除成功！");
    }
}
