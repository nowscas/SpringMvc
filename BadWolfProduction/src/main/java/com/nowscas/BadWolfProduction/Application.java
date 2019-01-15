package com.nowscas.BadWolfProduction;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 * Класс запускает приложение.
 */
@SpringBootApplication
public class Application {

    /**
     * Метод запускает приложение.
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    /**
     * Метод устанавливает максимальные размеры файла, загружаемого на сервер, или выгружаемого с сервера.
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("50MB");
        factory.setMaxRequestSize("50MB");
        return factory.createMultipartConfig();
    }

    /**
     * Метод устанавливает время обращеня к серверу.
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory tomcatEmbedded(){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                //-1 means unlimited
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
        return tomcat;
    }
}