package com.tracker.asteroid.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBeans {

    @Bean
    public OkHttpClient httpClient(){
        return new OkHttpClient();
    }
}
