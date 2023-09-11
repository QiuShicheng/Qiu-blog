package com.Qiu.service;

import com.Qiu.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface OssUploadService {
    //图片上传到七牛云
    ResponseResult uploadImg(MultipartFile img);
}