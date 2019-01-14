package com.nowscas.BadWolfProduction.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.imagePath}")
    private String uploadImagePath;

    @Value("${upload.musicPath}")
    private String uploadMusicPath;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/***")
                .addResourceLocations("file://" + uploadImagePath + "/");
        registry.addResourceHandler("/audio/***")
                .addResourceLocations("file://" + uploadMusicPath + "/");
        registry.addResourceHandler("/static/***")
                .addResourceLocations("classpath:/static/");
    }
}