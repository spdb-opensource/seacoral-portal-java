//package com.bsg.upm.config;
//
//
//import com.bsg.upm.interceptor.CorsInterceptor;
//import com.bsg.upm.interceptor.LoginInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import javax.annotation.Resource;
//
//@Configuration
//@EnableWebMvc
//public class WebConfig extends WebMvcConfigurerAdapter {
//    @Resource
//    private LoginInterceptor loginInterceptor;
//    @Resource
//    private CorsInterceptor corsInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //需将跨域拦截器放在最上面
//        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/**/*.*", "/login", "/logout", "/app/**", "/extends/**");
//    }
//}