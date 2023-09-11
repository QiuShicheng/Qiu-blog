package com.Qiu.service;

import com.Qiu.domain.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Qiu.domain.entity.Comment;

public interface CommentService extends IService<Comment> {

    //查询评论区的评论
    ResponseResult commentList(String commentType,Long articleId, Integer pageNum, Integer pageSize);

    //在文章的评论区发送评论
    ResponseResult addComment(Comment comment);
}