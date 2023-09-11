package com.Qiu.service;

import com.Qiu.domain.ResponseResult;
import com.Qiu.domain.vo.PageVo;
import com.Qiu.domain.vo.TagVo;
import com.Qiu.dto.TagListDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Qiu.domain.entity.Tag;

import java.util.List;

public interface TagService extends IService<Tag> {
    //查询标签列表
    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);
    //新增标签
    ResponseResult addTag(TagListDto tagListDto);
    //删除标签
    ResponseResult deleteTag(Long id);
    //修改标签-①根据标签的id来查询标签
    ResponseResult getLableById(Long id);
    //修改标签-②根据标签的id来修改标签
    ResponseResult myUpdateById(TagVo tagVo);

    //写博文-查询文章标签的接口
    List<TagVo> listAllTag();
}
