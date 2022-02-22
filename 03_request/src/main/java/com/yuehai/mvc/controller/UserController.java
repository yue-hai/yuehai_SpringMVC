package com.yuehai.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 月海
 * @create 2022/1/24 22:15
 */

// @Controller注解，标识该类为控制器
@Controller
public class UserController {

    /**
     * 使用 RESTFul 模拟用户资源的增删改查，一套，5个
     * /user    ；   请求方式：GET    ；   查询所有用户信息
     * /user/1  ；   请求方式：GET    ；   根据用户id查询用户信息
     * /user    ；   请求方式：post   ；   添加用户信息
     * /user    ；   请求方式：PUT    ；   更新用户信息
     * /user/1  ；   请求方式：DELETE ；   根据用户id删除用户信息
     */

    // /user    ；   请求方式：GET    ；   查询所有用户信息
    // 配置请求地址
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getAllUser(){
        System.out.println("查询所有用户信息");
        return "success";
    }

    // /user/{id}；  请求方式：GET    ；   根据id查询用户信息
    // 配置请求地址
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String getUserById(@PathVariable("id") String id){
        System.out.println("根据id查询用户信息：" + id);
        return "success";
    }

    // /user    ；   请求方式：post   ；   添加用户信息
    // 配置请求地址
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String inserUser(String username,String password){
        System.out.println("添加用户信息：" + username + "，" + password);
        return "success";
    }

    // /user    ；   请求方式：PUT    ；   更新用户信息
    // 配置请求地址
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String updateUser(String username,String password){
        System.out.println("修改用户信息：" + username + "，" + password);
        return "success";
    }

    // /user/1  ；   请求方式：DELETE ；   根据用户id删除用户信息
    // 配置请求地址
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") String id){
        System.out.println("根据id删除用户信息：" + id);
        return "success";
    }

}










