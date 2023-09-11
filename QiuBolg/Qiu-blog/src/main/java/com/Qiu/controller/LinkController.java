package com.Qiu.controller;

import com.Qiu.annotation.Systemlog;
import com.Qiu.domain.ResponseResult;
import com.Qiu.domain.entity.Link;
import com.Qiu.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    //LinkService是我们在huanf-framework工程写的接口
    private LinkService linkService;

    //--------------------------------查询友链列表-------------------------------------

    @GetMapping("/getAllLink")
    @Systemlog(businessName = "获得友链")//接口描述，用于'日志记录'功能
    //ResponseResult是我们在Qiu-framework工程写的实体类
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }


    //-------------------------------增加友链----------------------------------

    @PostMapping
    public ResponseResult add(@RequestBody Link link){
        linkService.save(link);
        return ResponseResult.okResult();
    }
    //-------------------------------修改友链---------------------------------

    @GetMapping(value = "/{id}")
    //①根据id查询友链
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        Link link = linkService.getById(id);
        return ResponseResult.okResult(link);
    }

    @PutMapping("/changeLinkStatus")
    //②修改友链状态
    public ResponseResult changeLinkStatus(@RequestBody Link link){
        linkService.updateById(link);
        return ResponseResult.okResult();
    }

    @PutMapping
    //③修改友链
    public ResponseResult edit(@RequestBody Link link){
        linkService.updateById(link);
        return ResponseResult.okResult();
    }
    //-------------------------------删除友链---------------------------------

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        linkService.removeById(id);
        return ResponseResult.okResult();
    }
}
