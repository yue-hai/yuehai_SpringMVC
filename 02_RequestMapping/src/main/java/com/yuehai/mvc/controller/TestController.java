package com.yuehai.mvc.controller;

import com.yuehai.mvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author 月海
 * @create 2022/1/22 21:16
 */

// @Controller注解，标识该类为控制器
@Controller
public class TestController {

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

    // @RequestMapping("/target")
    @RequestMapping(value = {"/t?*","/**/t*"})
    public String totarget(){
        // 设置返回（进入）的视图的名称
        return "target";
    }

    /**
     * @RequestMapping注解的属性：
     * value属性是一个字符串类型的数组，请求地址中只要满足其中一个就可以
     * method属性通过请求的请求方式（get或post）匹配请求映射
     * params属性通过请求的请求参数匹配请求映射
     * headers属性通过请求的请求头信息匹配请求映射
     */
    @RequestMapping(
            value = {"/testRequestMapping","/test"},
            method = {RequestMethod.GET,RequestMethod.POST},
            /**
             * username：请求参数必须携带username参数，有没有值无所谓
             * !username：请求参数不能携带username参数
             * username=admin：请求参数必须携带username参数，且值必须为admin
             * username!=admin：请求参数必须携带username参数，但是值不能为admin
            */
            params = {"username=admin"},
            /**
             * "header"：要求请求映射所匹配的请求必须携带header请求头信息
             * "!header"：要求请求映射所匹配的请求必须不能携带header请求头信息
             * "header=value"：要求请求映射所匹配的请求必须携带header请求头信息且header=value
             * "header!=value"：要求请求映射所匹配的请求必须携带header请求头信息且header!=value
            */
            headers = {"accept","Host=localhost:8080"}
    )
    public String totestRequestMapping(){
        // 设置返回（进入）的视图的名称
        return "testRequestMapping";
    }

    // SpringMVC支持路径中的占位符
    @RequestMapping("target2/{id}/{username}")
    // @PathVariable("id") String id：获取占位符请求参数中的id的值，并赋值给String类型的形参id
    public String totarget2(@PathVariable("id") String id,@PathVariable("username") String username){
        System.out.println("id：" + id + "，username：" + username);
        // 设置返回（进入）的视图的名称
        return "target";
    }

    // 通过ServletAPI获取请求参数
    @RequestMapping("/testServletAPI")
    // 形参位置的 request 标识当前请求
    public String testServletAPI(HttpServletRequest request){
        String username = request.getParameter("username");
        System.out.println(username);


        return "target";
    }

    // 通过控制器方法的形参获取请求参数
    @RequestMapping("/testParam")
    // 形参位置的形参要与请求参数的名称相同
    // String hobby：复选框内容，以字符串的形式接收
    // 也可以写成String[] hobby，表示以字符串数组的方式接收
    public String testParam(String username,String password,String[] hobby){
        System.out.println(username);
        System.out.println(password);
        // 打印复选框的内容，字符串形式，每个参数用 , 隔开
        // System.out.println(hobby);
        // 打印复选框的内容，字符串数组的形式
        System.out.println(Arrays.toString(hobby));

        return "target";
    }

    // 配置请求地址
    @RequestMapping("/testRequestParam")
    // 形参位置的形参要与请求参数的名称相同
    // String hobby：复选框内容，以字符串的形式接收
    // 也可以写成String[] hobby，表示以字符串数组的方式接收
    public String testRequestParam(
            /**
             * value：指定为形参赋值的请求参数的参数名，若是前端属性必须用user_name，那么就要在控制器将user_name映射为username
             * required：设置是否必须传输此请求参数，默认值为true，必须传递，没有传递参数则显示404，
             * 若设置为false，则当前请求不是必须传输value所指定的请求参数，若没有传输，则注解所标识的形参的值为null
             *defaultValue：不管required属性值为true或false，当value所指定的请求参数没有传输或传输的值为""时，则使用默认值为形参赋值
             */
            @RequestParam(value = "user_name",required = false) String username, String password,
            // 将请求头信息和控制器方法的形参创建映射关系
            @RequestHeader(value = "Accept") String  accept,
            // cookie数据和控制器方法的形参创建映射关系，此处获取的 session
            @CookieValue(value = "JSESSIONID",required = false,defaultValue = "yuehai") String JSESSIONID
    ){
        System.out.println(username);
        System.out.println(password);
        System.out.println(accept);
        System.out.println(JSESSIONID);

        return "target";
    }

    // 配置请求地址
    @RequestMapping("/testRequestHeader")
    // 形参位置的形参要与请求参数的名称相同
    // String hobby：复选框内容，以字符串的形式接收
    // 也可以写成String[] hobby，表示以字符串数组的方式接收
    public String testRequestHeader(
            // 将请求头信息和控制器方法的形参创建映射关系
            @RequestHeader(value = "Accept") String  accept
    ){
        System.out.println(accept);

        return "target";
    }

    // 创建 session
    @RequestMapping("/addSession")
    // 形参位置的 request 标识当前请求
    public String addSession(HttpServletRequest request){
        request.getSession();

        return "index";
    }

    // 配置请求地址
    @RequestMapping("/testCookieValue")
    // 形参位置的形参要与请求参数的名称相同
    // String hobby：复选框内容，以字符串的形式接收
    // 也可以写成String[] hobby，表示以字符串数组的方式接收
    public String testCookieValue(
            // cookie数据和控制器方法的形参创建映射关系，此处获取的 session
            @CookieValue(value = "JSESSIONID",required = false,defaultValue = "yuehai") String JSESSIONID
    ){
        System.out.println(JSESSIONID);

        return "target";
    }

    // 通过POJO获取请求参数
    // 配置请求地址
    @RequestMapping("/testPojo")
    public String testPojo(User user){
        System.out.println(user);
        return "target";
    }

}


























