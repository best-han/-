package com.windaka.suizhi.mpi;

import com.windaka.suizhi.mpi.websocket.SocketServer;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@ServletComponentScan
public class SjwlMpiApplication {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("config.yml"));
        configurer.setProperties(yaml.getObject());
        return configurer;
    }

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(SjwlMpiApplication.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        SocketServer.setApplicationContext(configurableApplicationContext);
//        try {
//            ShellUtil.executeCommand("E:/software/nginx-rtmp-win32-dev/nginx.exe");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
