package com.yuehai.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author 月海
 * @create 2022/1/24 19:34
 */

// @Controller注解，标识该类为控制器
@Controller
public class ViewController {

    // 配置请求地址
    @RequestMapping("/testThymeleafView")
    // 使用 ThymeleafView
    public String testThymeleafView(){
        /**
         * 当控制器方法中所设置的视图名称没有任何前缀时，
         * 此时的视图名称会被SpringMVC配置文件中所配置的视图解析器解析，
         * 视图名称拼接视图前缀和视图后缀所得到的最终路径，会通过转发的方式实现跳转
         */
        return "success";
    }

    // 配置请求地址
    @RequestMapping("/testForward")
    // 使用转发视图
    public String testForward(){
        /**
         * SpringMVC中默认的转发视图是InternalResourceView
         * 当控制器方法中所设置的视图名称以"forward:"为前缀时，创建InternalResourceView视图，
         * 此时的视图名称不会被SpringMVC配置文件中所配置的视图解析器解析，
         * 而是会将前缀"forward:"去掉，剩余部分作为最终路径通过转发的方式实现跳转
         * "forward:/testThymeleafView"：转发到testThymeleafView视图请求，不能直接转发到前端页面
         */
        return "forward:/testThymeleafView";
    }

    // 配置请求地址
    @RequestMapping("/testRedirect")
    // 使用重定向视图
    public String testRedirect(){
        /**
         * SpringMVC中默认的重定向视图是RedirectView
         * 当控制器方法中所设置的视图名称以"redirect:"为前缀时，创建RedirectView视图，
         * 此时的视图名称不会被SpringMVC配置文件中所配置的视图解析器解析，
         * 而是会将前缀"redirect:"去掉，剩余部分作为最终路径通过重定向的方式实现跳转
         * "redirect:/testThymeleafView"：重定向到testThymeleafView视图请求，不能直接转发到前端页面
         */
        return "redirect:/testThymeleafView";
    }

}
