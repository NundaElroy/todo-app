package com.nunda.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


@Configuration
public class UserManagementConfig {
    @Bean
    public UserDetails userDetails() {

        return  User.withUsername("john@gmail.com")
                .password("12345")
                .authorities("read")
                .build();


    }


}
