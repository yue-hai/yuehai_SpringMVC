package com.yuehai.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 月海
 * @create 2022/1/26 21:37
 */

// @Controller注解，标识该类为控制器
@Controller
public class TestController {

    // 配置请求地址
    @RequestMapping("/testInterceptor")
    public String testInterceptor(){
        return "success";
    }

    // 配置请求地址
    @RequestMapping("/testExceptionHandler")
    public String testExceptionHandler(){
        // 模拟异常
        int i = 1 / 0;

        return "success";
    }

}
