package com.socialmediaapp.backendrestapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SocialMediaBackendProjectConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        return httpSecurity.authorizeHttpRequests(
                //postcomment
                auth->auth.requestMatchers("/api/register").permitAll()
                        .requestMatchers("/api/profile").authenticated()
                        .requestMatchers("/api/showprofile/**").authenticated()
                        .requestMatchers("/home").authenticated()
                        .requestMatchers("/api/createpost").authenticated()
                        .requestMatchers("/api/showpost/**").authenticated()
                        .requestMatchers("/api/myposts").authenticated()
                        .requestMatchers("/api/postcomment/**").authenticated()
                        .requestMatchers("/api/all-posts").authenticated()
                        .requestMatchers("/api/all-people/**").authenticated()
                        .requestMatchers("/api/send-friend-request/**").authenticated()
                        .requestMatchers("/api/get-friend-requests").authenticated()
                        .requestMatchers("/static/**").permitAll()
        ).formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
