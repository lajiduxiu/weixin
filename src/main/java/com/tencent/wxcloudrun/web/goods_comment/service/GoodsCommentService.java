package com.tencent.wxcloudrun.web.goods_comment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.web.goods_comment.entity.CommentParm;
import com.tencent.wxcloudrun.web.goods_comment.entity.GoodsComment;

import java.util.List;

/**
* @author 陈
* @description 针对表【goods_comment】的数据库操作Service
* @createDate 2024-07-31 18:07:54
*/
public interface GoodsCommentService extends IService<GoodsComment> {
    List<GoodsComment> commentList(Integer goodsId);
    IPage<GoodsComment> getList(CommentParm parm);

}
