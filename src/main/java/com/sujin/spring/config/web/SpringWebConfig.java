package com.sujin.spring.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**")
            .addResourceLocations("/resources/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("pages/security/auth/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/logout").setViewName("pages/security/auth/logout");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
