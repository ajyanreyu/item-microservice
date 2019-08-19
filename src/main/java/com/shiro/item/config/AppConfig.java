package com.shiro.item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    /**
     * Http client that will allow us to access the product microservice
     * @return RestTemplate
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    @Bean("restClient")
    public RestTemplate registerRestTemplate(){
        return new RestTemplate();
    }
}
