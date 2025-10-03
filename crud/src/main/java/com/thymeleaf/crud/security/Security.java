package com.thymeleaf.crud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class Security {

    @Bean
    public UserDetailsManager userdetailsmanager(DataSource datasource) {

        return new JdbcUserDetailsManager(datasource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                        configurer ->
                                configurer
                                        .requestMatchers(HttpMethod.GET, "/employees/list").hasRole("EMPLOYEE")
                                        .requestMatchers(HttpMethod.GET, "/employees/list").hasRole("MANAGER")
                                        .requestMatchers(HttpMethod.POST, "/employees/save").hasRole("MANAGER")
                                        .requestMatchers(HttpMethod.GET, "/employees/showformforupdate").hasRole("MANAGER")
                                        .requestMatchers(HttpMethod.GET, "/employees/list").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.POST, "/employees/save").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/employees/showformforupdate").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/employees/delete").hasRole("ADMIN")
                                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );

        return http.build();
    }
}

