package com.yuehai.mvc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 月海
 * @create 2022/1/26 21:43
 */

// 创建 bean 实例
@Component
// 拦截器，实现 HandlerInterceptor 接口，重写下面三个方法
public class FirstInterceptor implements HandlerInterceptor {

    // 在控制器方法执行之前执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("FirstInterceptor拦截器的preHandle方法");
        // 返回 false 表示拦截，返回 true 表示放行
        return true;
    }

    // 在控制器方法执行之后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("FirstInterceptor拦截器的postHandle方法");
    }

    // 在视图渲染之后执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("FirstInterceptor拦截器的afterCompletion方法");
    }
}
