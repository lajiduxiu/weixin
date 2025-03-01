package com.tencent.wxcloudrun.web.goods_comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.web.goods_comment.entity.GoodsComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 陈
* @description 针对表【goods_comment】的数据库操作Mapper
* @createDate 2024-07-31 18:07:54
* @Entity com.tencent.wxcloudrun.web.goods_comment.entity.GoodsComment
*/
public interface GoodsCommentMapper extends BaseMapper<GoodsComment> {
    List<GoodsComment> commentList(@Param("goodsId") Integer goodsId);
    IPage<GoodsComment> getList(Page<GoodsComment> page);
}




