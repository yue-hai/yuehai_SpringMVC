package com.yuehai.mvc.Controller;

import com.yuehai.mvc.bean.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 月海
 * @create 2022/1/25 21:42
 */

// @Controller注解，标识该类为控制器
@Controller
public class HttpController {

    // 配置请求地址
    @RequestMapping("/testRequestBody")
    // 使用 @RequestBody 注解将请求参数赋值给形参 requestBody
    public String testRequestBody(@RequestBody String requestBody){
        System.out.println("requestBody：" + requestBody);

        return "success";
    }

    // 配置请求地址
    @RequestMapping("/testRequestEntity")
    // 使用 RequestEntity 封装请求报文并赋值给形参 requestEntity
    public String testRequestEntity(RequestEntity<String> requestEntity){
        // getHeaders()：获取请求头信息
        System.out.println("requestHeader请求头："+ requestEntity.getHeaders());
        // getBody()获取请求体
        System.out.println("requestBody请求体："+ requestEntity.getBody());

        return "success";
    }

    // 配置请求地址
    @RequestMapping("/testHttpServletResponse")
    // 通过 HttpServletResponse 响应浏览器数据
    public void testHttpServletResponse(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello,HttpServletResponse");
    }

    // 配置请求地址
    @RequestMapping("/testResponseBody")
    // @ResponseBody用于标识一个控制器方法，
    // 可以将该方法的返回值直接作为响应报文的响应体响应到浏览器
    @ResponseBody
    public String testResponseBody() {
        return "success";
    }

    // 配置请求地址
    @RequestMapping("/testResponseBodyUser")
    // @ResponseBody用于标识一个控制器方法，
    // 可以将该方法的返回值直接作为响应报文的响应体响应到浏览器，返回对象
    @ResponseBody
    public User testResponseBodyUser() {
        return new User(0,"yan","000123",14,"2");
    }

    // 配置请求地址
    @RequestMapping("/testAjax")
    // @ResponseBody用于标识一个控制器方法，
    // 可以将该方法的返回值直接作为响应报文的响应体响应到浏览器，返回对象
    @ResponseBody
    public String testAjax(String username,String password) {
        System.out.println("username：" + username + "，password：" + password);

        return "hello,ajax";
    }

}
