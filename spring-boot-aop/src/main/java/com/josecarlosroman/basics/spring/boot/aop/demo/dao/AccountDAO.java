package com.josecarlosroman.basics.spring.boot.aop.demo.dao;

import com.josecarlosroman.basics.spring.boot.aop.demo.Account;

import java.util.List;

public interface AccountDAO {

    public void addAccount(Account account, boolean vipFlag);

    public List<Account> findAccounts();
    public List<Account> findAccounts(boolean tripWire);

    public boolean doSomething();

    // in order to be able to call getters and setters
    public String getType();
    public void setType(String name);
    public String getCode();
    public void setCode(String code);
}
