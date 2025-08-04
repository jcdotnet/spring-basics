package com.josecarlosroman.basics.spring.boot.core.rest;

import com.josecarlosroman.basics.spring.boot.core.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoreRestController {

    private Coach myCoach; // private field for the dependency

    // the Autowired annotation tells Spring to inject a dependency
    // @Autowired is optional when we have only one constructor
    @Autowired
    public CoreRestController(Coach theCoach) {
        myCoach = theCoach;
    }

    // we can also use setter injection (and method injection)
    /*
    @Autowired
    public void setMyCoach(Coach theCoach) {
        myCoach = theCoach;
    }
    */
    /*
    @Autowired
    public void doSomeStuff(Coach theCoach) {
        myCoach = theCoach;
    }
    */

    @GetMapping("/daily-workout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
