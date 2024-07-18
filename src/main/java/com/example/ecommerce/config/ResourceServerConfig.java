package com.example.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Menonaktifkan CSRF untuk sementara, perlu ditinjau di lingkungan produksi
            .authorizeRequests()  
            .antMatchers("/inventory/**", "/orders/**").authenticated()
            .anyRequest().permitAll() .and()
            .formLogin().permitAll();


        http.headers().frameOptions().disable(); // Nonaktifkan X-Frame-Options untuk H2 Console

        return http.build();
    }
}
