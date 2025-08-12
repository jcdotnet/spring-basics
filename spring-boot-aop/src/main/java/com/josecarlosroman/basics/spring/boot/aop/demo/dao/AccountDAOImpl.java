package com.josecarlosroman.basics.spring.boot.aop.demo.dao;

import com.josecarlosroman.basics.spring.boot.aop.demo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository // component scanning
public class AccountDAOImpl implements AccountDAO{

    private String type;
    private String code;

    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK (ADDING AN ACCOUNT)");
    }

    @Override
    public List<Account> findAccounts() {
        return this.findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        // simulate an exception
        if (tripWire) {
            throw new RuntimeException("No soup for you!");
        }

        List<Account> accounts = new ArrayList<>();

        Account temp1 = new Account("John", "Silver");
        Account temp2 = new Account("Jane", "Platinum");
        Account temp3 = new Account("David", "Gold");

        accounts.add(temp1);
        accounts.add(temp2);
        accounts.add(temp3);

        return accounts;
    }

    @Override
    public boolean doSomething() {
        System.out.println(getClass() + ": doSomething()");
        return true;
    }

    public String getType() {
        System.out.println(getClass() + ": getType()");
        return type;
    }

    public void setType(String type) {
        System.out.println(getClass() + ": setType()");
        this.type = type;
    }

    public String getCode() {
        System.out.println(getClass() + ": getCode()");
        return code;
    }

    public void setCode(String code) {
        System.out.println(getClass() + ": setCode()");
        this.code = code;
    }
}
