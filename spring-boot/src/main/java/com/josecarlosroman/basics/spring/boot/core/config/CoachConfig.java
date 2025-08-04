package com.josecarlosroman.basics.spring.boot.core.config;

import com.josecarlosroman.basics.spring.boot.core.common.Coach;
import com.josecarlosroman.basics.spring.boot.core.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoachConfig {

    @Bean
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
