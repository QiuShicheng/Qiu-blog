package com.Qiu.controller;

import com.Qiu.annotation.Systemlog;
import com.Qiu.domain.ResponseResult;
import com.Qiu.service.OssUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UploadController {

    @Autowired
    //UploadService是我们在huanf-framework写的接口
    private OssUploadService ossUploadService;

    @PostMapping("/upload")
    @Systemlog(businessName = "上传头像")//接口描述，用于'日志记录'功能
    //MultipartFile是spring提供的接口，ResponseResult是我们在Qiu-framework写的实体类
    public ResponseResult uploadImg(MultipartFile img){
        //图片上传到七牛云
        return ossUploadService.uploadImg(img);
    }
}