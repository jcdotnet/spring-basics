// uncomment if no DB support
/*
package com.josecarlosroman.basics.spring.boot.rest.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// Spring Security Demo (Authorization: basic auth by default)
// Spring will use the username/pass that we create in this config
// remember tha Spring created a default user (user) and we changed
// the default user's password in the properties file. Before that,
// a password was given in the Spring Boot log output
@Configuration
public class UsersConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")  // plain text
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")  // plain text
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")  // plain text
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }

}
*/