package com.tencent.wxcloudrun.web.goods_comment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.web.goods_comment.entity.CommentParm;
import com.tencent.wxcloudrun.web.goods_comment.entity.GoodsComment;
import com.tencent.wxcloudrun.web.goods_comment.mapper.GoodsCommentMapper;
import com.tencent.wxcloudrun.web.goods_comment.service.GoodsCommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈
* @description 针对表【goods_comment】的数据库操作Service实现
* @createDate 2024-07-31 18:07:54
*/
@Service
public class GoodsCommentServiceImpl extends ServiceImpl<GoodsCommentMapper, GoodsComment>
    implements GoodsCommentService{

    @Override
    public List<GoodsComment> commentList(Integer goodsId) {
        return this.baseMapper.commentList(goodsId);
    }

    @Override
    public IPage<GoodsComment> getList(CommentParm parm) {
        Page<GoodsComment> page = new Page<>(parm.getCurrentPage(), parm.getPageSize());
        return this.baseMapper.getList(page);
    }
}




