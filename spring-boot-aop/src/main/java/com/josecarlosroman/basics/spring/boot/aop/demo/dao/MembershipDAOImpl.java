package com.josecarlosroman.basics.spring.boot.aop.demo.dao;

import org.springframework.stereotype.Repository;

@Repository // component scanning
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public void addAccount() {
        System.out.println(getClass() + ": DOING MY DB WORK (ADDING AN ACCOUNT)");
    }
}
