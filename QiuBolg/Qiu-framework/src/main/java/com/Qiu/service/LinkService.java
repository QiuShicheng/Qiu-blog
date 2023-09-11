package com.Qiu.service;

import com.Qiu.domain.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Qiu.domain.entity.Link;
import com.Qiu.domain.ResponseResult;

public interface LinkService extends IService<Link> {

    //查询友链
    ResponseResult getAllLink();
    //分页查询友链
    PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize);

}
