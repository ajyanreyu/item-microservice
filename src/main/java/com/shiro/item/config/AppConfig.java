package com.shiro.item.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    /**
     * Http client that will allow us to access the product microservice.
     * <i>with the note loadBalance uses ribbon </i>
     * @return RestTemplate
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    @Bean("restClient")
    @LoadBalanced
    public RestTemplate registerRestTemplate(){
        return new RestTemplate();
    }
}
