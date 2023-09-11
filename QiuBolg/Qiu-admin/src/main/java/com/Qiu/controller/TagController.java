package com.Qiu.controller;

import com.Qiu.domain.ResponseResult;
import com.Qiu.domain.entity.Tag;
import com.Qiu.domain.vo.PageVo;
import com.Qiu.domain.vo.TagVo;
import com.Qiu.dto.AddTagDto;
import com.Qiu.dto.EditTagDto;
import com.Qiu.dto.TagListDto;
import com.Qiu.service.TagService;
import com.Qiu.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    //TagService是我们在Qiu-framework工程的接口
    private TagService tagService;

    //查询标签列表
    @GetMapping("/list")
    //ResponseResult是我们在Qiu-framework工程的实体类
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        //pageTagList是我们在Qiu-framework工程写的方法
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }
    //新增标签功能
    @PostMapping
    public ResponseResult add(@RequestBody AddTagDto tagDto){
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        tagService.save(tag);
        return ResponseResult.okResult();
    }
    //删除标签功能
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        tagService.removeById(id);
        return ResponseResult.okResult();
    }
    //修改标签
    @GetMapping("/{id}")
    //①根据标签的id来查询标签
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        Tag tag = tagService.getById(id);
        return ResponseResult.okResult(tag);
    }

    @PutMapping
    //②根据标签的id来修改标签
    public ResponseResult edit(@RequestBody EditTagDto tagDto){
        Tag tag = BeanCopyUtils.copyBean(tagDto,Tag.class);
        tagService.updateById(tag);
        return ResponseResult.okResult();
    }
    //---------------------------写博文-查询文章标签的接口---------------------------

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }

}
