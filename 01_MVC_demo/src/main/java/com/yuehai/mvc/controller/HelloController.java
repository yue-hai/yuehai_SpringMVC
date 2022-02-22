package com.yuehai.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 月海
 * @create 2022/1/22 18:52
 */
// @Controller注解，标识该类为控制器
@Controller
public class HelloController {

    /**
     * @RequestMapping注解：处理请求和控制器方法之间的映射关系
     * @RequestMapping注解的value属性可以通过请求地址匹配请求，
     * / 表示的当前工程的上下文路径，localhost:8080/springMVC/
     * @return  设置视图名称
     */
    @RequestMapping("/")
    public String index() {
        // 设置返回（进入）的视图的名称，前缀与后缀已在配置文件中配置
        return "index";
    }

    @RequestMapping("/target")
    public String totarget(){
        // 设置返回（进入）的视图的名称
        return "target";
    }

}
