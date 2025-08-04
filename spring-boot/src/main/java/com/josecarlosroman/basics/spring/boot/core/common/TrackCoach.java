package com.josecarlosroman.basics.spring.boot.core.common;

import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach
{
    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k";
    }
}
