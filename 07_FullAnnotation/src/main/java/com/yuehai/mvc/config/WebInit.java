package com.yuehai.mvc.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * @author 月海
 * @create 2022/1/27 19:09
 */

// web 工程的初始化类，代替 web.xml 文件
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 指定 spring 的配置类
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // 指定 spring 的配置类的 class 对象
        return new Class[]{SpringConfig.class};
    }

    /**
     * 指定 SpringMVC 的配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        // 指定 SpringMVC 的配置类的 class 对象
        return new Class[]{WebConfig.class};
    }

    /**
     * 指定 DispatcherServlet 的映射规则，即 url-pattern
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        // 将数据以某种格式编码的方式输出
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceResponseEncoding(true);

        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();

        return new Filter[]{characterEncodingFilter,hiddenHttpMethodFilter};
    }

}
