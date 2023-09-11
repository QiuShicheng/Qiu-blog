package com.Qiu.controller;
import com.Qiu.annotation.Systemlog;
import com.Qiu.domain.ResponseResult;
import com.Qiu.domain.entity.User;
import com.Qiu.enums.AppHttpCodeEnum;
import com.Qiu.exception.SystemException;
import com.Qiu.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {

    @Autowired
    //BlogLoginService是我们在service目录写的接口
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    @Systemlog(businessName = "登录")//接口描述，用于'日志记录'功能
    //ResponseResult是我们在Qiu-framework工程里面写的实体类
    public ResponseResult login(@RequestBody User user){
        //如果用户在进行登录时，没有传入'用户名'
        if(!StringUtils.hasText(user.getUserName())){
            // 提示'必须要传用户名'。AppHttpCodeEnum是我们写的枚举类。SystemException是我们写的统一异常处理的类
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }
    @PostMapping("/logout")
    @Systemlog(businessName = "退出")//接口描述，用于'日志记录'功能
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
