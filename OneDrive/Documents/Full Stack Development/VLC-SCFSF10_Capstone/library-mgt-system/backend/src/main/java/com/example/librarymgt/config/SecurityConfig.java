// SecurityConfig.java
package com.example.librarymgt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
 // Bean to configure security rules for your endpoints
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Configure authorization for HTTP requests
            .authorizeHttpRequests(authorize -> authorize
                // Allow all requests to any endpoint to be accessed by anyone
                .anyRequest().permitAll()
            )
            // Disable CSRF protection for a stateless API
            .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
