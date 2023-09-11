package com.Qiu.controller;


import com.Qiu.annotation.Systemlog;
import com.Qiu.domain.ResponseResult;
import com.Qiu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    @Systemlog(businessName = "获取类别列表")//接口描述，用于'日志记录'功能
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();
    }

}
