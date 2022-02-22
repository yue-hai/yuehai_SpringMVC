package com.yuehai.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 月海
 * @create 2022/1/24 15:55
 */

// @Controller注解，标识该类为控制器
@Controller
public class ScopeController {

    // 配置请求地址
    @RequestMapping("/testRequestByServletAPI")
    // 使用 servletAPI 向 request 域对象共享数据
    public String testRequestByServletAPI(HttpServletRequest request){
        // 设置 request 域的内容，参数1为名称，参数2为值
        request.setAttribute("testScope", "hello,servletAPI");

        return "success";
    }

    // 配置请求地址
    @RequestMapping("/testModelAndView")
    // 使用 ModelAndView 向 request 域对象共享数据
    public ModelAndView testModelAndView(){
        /**
         * ModelAndView有Model和View的功能
         * Model主要用于向请求域共享数据
         * View主要用于设置视图，实现页面跳转
         */
        ModelAndView mav = new ModelAndView();
        // 向请求域共享数据，参数1为名称，参数2为值
        mav.addObject("testScope", "hello,ModelAndView");
        // 设置视图（页面）名称，实现页面跳转
        mav.setViewName("success");

        // ModelAndView 必须作为该方法的返回值返回
        return mav;
    }

    // 配置请求地址
    @RequestMapping("/testModel")
    // 使用 Model 向 request 域对象共享数据
    public String testModel(Model model){
        // 向请求域共享数据，参数1为名称，参数2为值
        model.addAttribute("testScope", "hello,Model");

        return "success";
    }

    // 配置请求地址
    @RequestMapping("/testMap")
    // 使用 Map 向 request 域对象共享数据
    public String testMap(Map<String,Object> map){
        // 向请求域共享数据，参数1为名称，参数2为值
        map.put("testScope", "hello,Map");

        return "success";
    }

    // 配置请求地址
    @RequestMapping("/testModelMap")
    // 使用 ModelMap 向 request 域对象共享数据
    public String testModelMap(ModelMap modelMap){
        // 向请求域共享数据，参数1为名称，参数2为值
        modelMap.addAttribute("testScope", "hello,ModelMap");

        return "success";
    }

    // 配置请求地址
    @RequestMapping("/testSession")
    // 使用 servletAPI 向 Session 域对象共享数据
    public String testSession(HttpSession session){
        // 设置 session 域的内容，参数1为名称，参数2为值
        session.setAttribute("testSessionScope", "hello,servletAPI");

        return "success";
    }

    // 配置请求地址
    @RequestMapping("/testApplication")
    // 使用 servletAPI 向 application 域共享数据
    public String testApplication(HttpSession session){
        // application 是在服务器启动时创建的，在整个工程都有效
        // 创建 application 对象
        ServletContext application = session.getServletContext();
        // 向 application 对象中添加数据，参数1为名称，参数2为值
        application.setAttribute("testApplicationScope", "hello,application");

        return "success";
    }

}
