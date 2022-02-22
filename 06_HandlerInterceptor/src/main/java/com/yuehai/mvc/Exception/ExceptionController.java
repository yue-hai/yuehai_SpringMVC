package com.yuehai.mvc.Exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 月海
 * @create 2022/1/27 18:18
 */

// @ControllerAdvice 将当前类标识为异常处理的组件
@ControllerAdvice
public class ExceptionController {

    //@ExceptionHandler 用于设置所标识方法处理的异常，可以数组的方式写多个
    @ExceptionHandler(value = {ArithmeticException.class,NullPointerException.class})
    // 若是出现了异常，则会执行下面的方法来代替原先的 Controller 方法
    // exception 表示当前请求处理中出现的异常对象
    public String handleArithmeticException(Exception exception, Model model){
        // 将获取的错误信息使用 Model 向 request 域对象共享数据
        model.addAttribute("ex",exception);

        return "error";
    }
}
