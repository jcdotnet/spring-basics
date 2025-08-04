package com.josecarlosroman.basics.spring.boot.core.common;

import org.springframework.stereotype.Component;

// the Component annotation makes the class as a Spring Bean
// makes it available as dependency for dependency injection
@Component
public class CricketCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
