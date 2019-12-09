package com.ifi.trainer_ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestConfiguration {

    @Value("${trainer.service.username}")
    private String user;

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Value("${trainer.service.password}")
    private String password;

    @Bean
    public RestTemplate trainerApiRestTemplate(){
        var restTemplate =  restTemplate();
        restTemplate.setInterceptors(Arrays.asList(new BasicAuthenticationInterceptor(user, password)));
        return restTemplate;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}