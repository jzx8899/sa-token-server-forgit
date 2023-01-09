package com.yk.h5;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.spring.SpringMVCUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Sa-Token 配置类
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    /** 注册 [Sa-Token全局过滤器] */
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude("/sso/*", "/favicon.ico")
                .setAuth(obj -> {
                    if(!StpUtil.isLogin()) {
                        String back = SaFoxUtil.joinParam(SaHolder.getRequest().getUrl(), SpringMVCUtil.getRequest().getQueryString());
                        SaHolder.getResponse().redirect("/sso/login?back=" + SaFoxUtil.encodeUrl(back));
                        SaRouter.back();
                    }
                })
                ;
    }
}