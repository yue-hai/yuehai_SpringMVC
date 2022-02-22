package com.yuehai.mvc.config;

import com.yuehai.mvc.interceptor.FirstInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.List;
import java.util.Properties;

/**
 * @author 月海
 * @create 2022/1/27 19:20
 */

/**
 * 定义配置类，代替 springMVC 配置文件，作用：
 * 1、自动扫描包，扫描组件
 * 2、视图解析器
 * 3、视图控制器 view-controller
 * 4、静态资源处理 default-servlet-handler
 * 5、mvc注解驱动 annotation-driven
 * 6、文件上传解析器
 * 7、异常处理
 * 8、拦截器
 */

// 将当前类标识为一个配置类
@Configuration
// 1、自动扫描包，扫描组件
@ComponentScan("com.yuehai.mvc")
// 5、mvc注解驱动 annotation-driven
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    // 2、视图解析器
    // 解析视图的策略，templateResolver
    // 配置生成模板解析器
    @Bean
    public ITemplateResolver templateResolver() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        // ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过 WebApplicationContext 的方法获得
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }
    // 当前所用的模板，templateEngine
    // 生成模板引擎并为模板引擎注入模板解析器
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
    // 配置Thymeleaf视图解析器
    // 生成视图解析器并未解析器注入模板引擎
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }

    // 3、视图控制器 view-controller
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/success").setViewName("success");
    }

    // 4、静态资源处理 default-servlet-handler
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 代表当前默认的 servlet 资源可用
        configurer.enable();
    }

    // 6、文件上传解析器
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }

    // 7、异常处理
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

        Properties prop = new Properties();
        prop.setProperty("java.lang.ArithmeticExceptio","error");

        exceptionResolver.setExceptionMappings(prop);
        exceptionResolver.setExceptionAttribute("exception");

        resolvers.add(exceptionResolver);
    }

    // 8、拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建拦截器的实例
        FirstInterceptor firstInterceptor = new FirstInterceptor();
        // 调用 addInterceptor() 方法，并放入 firstInterceptor 拦截器对象
        // addPathPatterns()：设置拦截路径，可配置多个
        registry.addInterceptor(firstInterceptor).addPathPatterns("/*","/*/*");
        // excludePathPatterns()：排除拦截路径，可配置多个
        registry.addInterceptor(firstInterceptor).excludePathPatterns("/");
    }
}




















