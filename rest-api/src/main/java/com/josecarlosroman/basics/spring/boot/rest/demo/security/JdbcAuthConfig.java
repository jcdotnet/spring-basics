package com.josecarlosroman.basics.spring.boot.rest.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

// JDBC Auth (Authentication and Authorization)
// using Spring Security's predefined tables schemas:
// user, authorities, {id}encodedPassword, ROLE_, etc
@Configuration
public class JdbcAuthConfig {

    // JDBC Auth with our data source
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        return new JdbcUserDetailsManager(dataSource);

        // use this code if using custom table names
        /*
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
        */
    }
}
