package com.Qiu.controller;

import com.Qiu.annotation.Systemlog;
import com.Qiu.domain.ResponseResult;
import com.Qiu.domain.entity.User;
import com.Qiu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    //UserService是我们在huanf-framework工程写的接口
    private UserService userService;

    @GetMapping("/userInfo")
    @Systemlog(businessName = "查询个人信息")//接口描述，用于'日志记录'功能
    public ResponseResult userInfo(){
        //查询个人信息
        return userService.userInfo();
    }

    @PutMapping("userInfo")
    @Systemlog(businessName = "更新用户信息")//接口描述，用于'日志记录'功能
    public ResponseResult  updateUserInfo(@RequestBody User user){
        //更新个人信息
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @Systemlog(businessName = "用户注册")//接口描述，用于'日志记录'功能
    public ResponseResult register(@RequestBody User user){
        //注册功能
        return userService.register(user);
    }
}
