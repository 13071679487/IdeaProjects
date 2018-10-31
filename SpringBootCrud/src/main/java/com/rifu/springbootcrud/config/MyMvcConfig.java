package com.rifu.springbootcrud.config;

import com.rifu.springbootcrud.component.CustomLocaleResolver;
import com.rifu.springbootcrud.component.LoginHandlerInterceptor;
import com.rifu.springbootcrud.filter.CustomFilter;
import com.rifu.springbootcrud.listener.CustomListener;
import com.rifu.springbootcrud.servlet.CustomServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.util.Arrays;

/**
 * @Author Rifu
 * @Date 2018/9/13  20:46
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        return new ServletListenerRegistrationBean(new CustomListener());
    }

    /**
     * 注册三大组件中Filter
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/custom"));
        return registrationBean;
    }

    /**
     * 注册三大组件中的Servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean(new CustomServlet(),"/custom");
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("backend");
//                registry.addViewController("/index").setViewName("backend");
//                registry.addViewController("/index.html").setViewName("backend");
//                   registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("backend");

            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/login","/user/login","/index.html");
            }
        };
    }


    /**
     * 注册语言解析器
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new CustomLocaleResolver();
    }
}
