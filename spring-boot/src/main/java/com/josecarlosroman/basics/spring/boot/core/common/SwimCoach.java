package com.josecarlosroman.basics.spring.boot.core.common;

import org.springframework.context.annotation.Configuration;

// @Component // this is a config bean
// @Configuration // configuring this bean in CoachConfig instead
public class SwimCoach implements Coach {

    /*
    public SwimCoach(){
        System.out.println("In constructor" + getClass().getSimpleName());
    }
    */
    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters as a warm up";
    }
}
