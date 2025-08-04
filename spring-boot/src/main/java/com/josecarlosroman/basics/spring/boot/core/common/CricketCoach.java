package com.josecarlosroman.basics.spring.boot.core.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// the Component annotation makes the class as a Spring Bean
// makes it available as dependency for dependency injection
@Component
// @Lazy the given bean will only be initialized when needed
// (when it is needed for dependency injection in this demo)
@Lazy
// @Primary
public class CricketCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
