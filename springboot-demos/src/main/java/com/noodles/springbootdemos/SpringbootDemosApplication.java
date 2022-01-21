package com.noodles.springbootdemos;

import java.sql.Connection;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringbootDemosApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemosApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("服务已启动");
    }

    /**
     * 配置同时支持http和https
     * @author 巫威
     * @date 2022/1/21 16:49
     */
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }

    private Connector createStandardConnector() {
        Connector  connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(8080);
        return connector;
    }

}
