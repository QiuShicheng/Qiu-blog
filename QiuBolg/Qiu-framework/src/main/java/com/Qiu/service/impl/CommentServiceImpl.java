package com.Qiu.service.impl;

import com.Qiu.constants.SystemCanstants;
import com.Qiu.domain.ResponseResult;
import com.Qiu.domain.vo.CommentVo;
import com.Qiu.domain.vo.PageVo;
import com.Qiu.enums.AppHttpCodeEnum;
import com.Qiu.exception.SystemException;
import com.Qiu.service.UserService;
import com.Qiu.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Qiu.domain.entity.Comment;
import com.Qiu.mapper.CommentMapper;
import com.Qiu.service.ArticleService;
import com.Qiu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService{

    @Autowired
    //根据userid查询用户信息，也就是查username
    private UserService userService;

    @Override
    //查询评论区的评论
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        //对articleId进行判断，作用是得到指定的文章。如果是文章评论，才会判断articleId，避免友链评论判断articleId时出现空指针
        queryWrapper.eq(SystemCanstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId);

        //对评论区的某条评论的rootID进行判断，如果为-1，就表示是根评论。SystemCanstants是我们写的解决字面值的类
        queryWrapper.eq(Comment::getRootId, SystemCanstants.COMMENT_ROOT);

        //文章的评论，避免查到友链的评论
        queryWrapper.eq(Comment::getType,commentType);

        //分页查询。查的是整个评论区的每一条评论
        Page<Comment> page  = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);

        //调用下面那个方法。根评论排序
        List<Comment> sortedComments = page.getRecords().stream()
                .sorted(Comparator.comparing(Comment::getCreateTime).reversed())
                .collect(Collectors.toList());
        List<CommentVo> commentVoList = toCommentVoList(sortedComments);

        //遍历(可以用for循环，也可以用stream流)。查询子评论(注意子评论只查到二级评论，不再往深查)
        for (CommentVo commentVo : commentVoList) {
            //查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            //把查到的children子评论集的集合，赋值给commentVo类的children字段
            commentVo.setChildren(children);

        }

        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }

    //在文章的评论区发送评论
    @Override
    public ResponseResult addComment(Comment comment) {
        //注意前端在调用这个发送评论接口时，在请求体是没有向我们传入createTime、createId、updateTime、updateID字段，所以
        //我们这里往后端插入数据时，就会导致上面那行的四个字段没有值
        //为了解决这个问题，我们在huanf-framework工程新增了MyMetaObjectHandler类、修改了Comment类。详细可自己定位去看一下代码

        //限制用户在发送评论时，评论内容不能为空。如果为空就抛出异常
        if(!StringUtils.hasText(comment.getContent())){
            //AppHttpCodeEnum是我们写的枚举类，CONTENT_NOT_NULL代表提示''
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }

        //解决了四个字段没有值的情况，就可以直接调用mybatisplus提供的save方法往数据库插入数据(用户发送的评论的各个字段)了
        save(comment);

        //封装响应返回
        return ResponseResult.okResult();
    }

    //-------------------------------下面是一些方便调用的方法--------------------------------------

    //根据根评论的id，来查询对应的所有子评论(注意子评论只查到二级评论，不再往深查)
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);
        //对子评论按照时间进行排序
        queryWrapper.orderByDesc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);
        //调用下面那个方法
        List<CommentVo> commentVos = toCommentVoList(comments);
        return commentVos;
    }

    //封装响应返回。CommentVo、BeanCopyUtils、ResponseResult、PageVo是我们写的类
    private List<CommentVo> toCommentVoList(List<Comment> list){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //遍历vo集合
        for (CommentVo commentVo : commentVos) {
            //通过creatyBy查询用户的昵称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            //通过toCommentUserId查询用户的昵称并赋值
            //如果toCommentUserId不为-1才进行查询
            if(commentVo.getToCommentUserId()!=-1){
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        //返回给前端
        return commentVos;
    }
}