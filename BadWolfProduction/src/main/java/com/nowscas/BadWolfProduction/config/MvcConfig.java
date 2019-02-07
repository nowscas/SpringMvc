package com.nowscas.BadWolfProduction.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Класс содержащий конфигурацию web-слоя.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.imagePath}")
    private String uploadImagePath;
    @Value("${upload.postImagePath}")
    private String uploadPostImagePath;
    @Value("${upload.musicPath}")
    private String uploadMusicPath;
    @Value("${upload.decarationPath}")
    private String uploadDecarationPath;
    @Value("${upload.priceExamplePath}")
    private String uploadPriceAudioPath;

    /**
     * На данный момент в методе используется только встроенная в Spring система авторизации,
     * в которую передается путь обращения(/login) и шаблон страницы(login).
     * @param registry
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    /**
     * Метод перенаправляет по указанногму пути все обращения к серверу начинающиеся с определенных символов.
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/***")
                .addResourceLocations("file://" + uploadImagePath + "/");
        registry.addResourceHandler("/postImg/***")
                .addResourceLocations("file://" + uploadPostImagePath + "/");
        registry.addResourceHandler("/audio/***")
                .addResourceLocations("file://" + uploadMusicPath + "/");
        registry.addResourceHandler("/decoration/***")
                .addResourceLocations("file://" + uploadDecarationPath + "/");
        registry.addResourceHandler("/priceAudio/***")
                .addResourceLocations("file://" + uploadPriceAudioPath + "/");
        registry.addResourceHandler("/static/***")
                .addResourceLocations("classpath:/static/");
    }
}