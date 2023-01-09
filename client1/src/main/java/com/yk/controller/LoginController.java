package com.yk.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    /**
     * 登录方法
     * 1.用户名或者密码为空（null,""）
     * 2.用户名不存在
     * 3.密码错误
     * 4.用户名密码正确
     */
    @GetMapping ("/in")
    public String login() {
        //1.接受参数
        //2.调用service判断用户名密码是否正确
        //3.响应结果
        return "成功响应请求";
    }
}
