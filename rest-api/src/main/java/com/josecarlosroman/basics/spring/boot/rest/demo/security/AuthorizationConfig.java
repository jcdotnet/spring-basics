// uncomment if no DB support
/*
package com.josecarlosroman.basics.spring.boot.rest.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// Spring Security: restricting URLs based on user roles
@Configuration
public class AuthorizationConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests( config -> config
                .requestMatchers(
                        HttpMethod.GET, "api/employees"
                ).hasRole("EMPLOYEE")
                .requestMatchers(
                        HttpMethod.GET, "api/employees/**"
                ).hasRole("EMPLOYEE")
                .requestMatchers(
                        HttpMethod.POST, "api/employees"
                ).hasRole("MANAGER")
                .requestMatchers(
                        HttpMethod.PUT, "api/employees"
                ).hasRole("MANAGER")
                // Spring Data REST only: the ID is passed on the
                // URL by Spring Data REST when using PUT requests
                // This not necessary when using @RestController!
                .requestMatchers(
                        HttpMethod.PUT, "api/employees/**"
                ).hasRole("MANAGER")
                // PATCH: only implemented for @RestController
                //.requestMatchers(
                //        HttpMethod.PATCH, "api/employees/**"
                //).hasRole("MANAGER")
                .requestMatchers(
                        HttpMethod.DELETE, "api/employees/**"
                ).hasRole("ADMIN")
        );

        // use HTTP Basic Auth
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
*/