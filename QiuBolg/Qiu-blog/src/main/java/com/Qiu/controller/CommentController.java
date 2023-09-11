package com.Qiu.controller;

import com.Qiu.annotation.Systemlog;
import com.Qiu.constants.SystemCanstants;
import com.Qiu.domain.ResponseResult;
import com.Qiu.domain.entity.Comment;
import com.Qiu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    //CommentService是我们在Qiu-framework工程写的类
    private CommentService commentService;

    @GetMapping("commentList")
    @Systemlog(businessName = "文章评论")//接口描述，用于'日志记录'功能
    //ResponseResult是我们在Qiu-framework工程写的类
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemCanstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @PostMapping
    //在文章的评论区发送评论。ResponseResult是我们在huanf-framework工程写的类
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    @Systemlog(businessName = "友链评论")//接口描述，用于'日志记录'功能
    //在友链的评论区发送评论。ResponseResult是我们在Qiu-framework工程写的类
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        //commentService是我们刚刚实现文章的评论区发送评论功能时(当时用的是addComment方法，现在用commentList方法)，写的类
        //SystemCanstants是我们写的用来解决字面值的常量类，Article_LINK代表1
        return commentService.commentList(SystemCanstants.LINK_COMMENT,null,pageNum,pageSize);
    }
}
