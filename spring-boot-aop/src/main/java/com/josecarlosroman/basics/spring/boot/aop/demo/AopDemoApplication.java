package com.josecarlosroman.basics.spring.boot.aop.demo;

import com.josecarlosroman.basics.spring.boot.aop.demo.dao.AccountDAO;
import com.josecarlosroman.basics.spring.boot.aop.demo.dao.MembershipDAO;
import com.josecarlosroman.basics.spring.boot.aop.demo.services.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO,
											   MembershipDAO membershipDAO,
											   TrafficFortuneService trafficFortuneService) {
		return runner ->{
			System.out.println("AOP Demo Application");
			// uncomment as needed
			//beforeAdviceDemo(accountDAO, membershipDAO);
			//afterReturningAdviceDemo(accountDAO);
			//afterThrowingAdviceDemo(accountDAO);
			//afterAdviceDemo(accountDAO);
			//aroundAdviceDemo(trafficFortuneService);
			aroundAdviceHandleException(trafficFortuneService);
		};
	}

	private void aroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {

		System.out.println("\nMain Program: aroundAdviceHandleException");
		System.out.println("Calling getFortune()");

		String data = trafficFortuneService.getFortune(true); // throws exception

		System.out.println("\nMy fortune is: " + data);
	}

	private void aroundAdviceDemo(TrafficFortuneService trafficFortuneService) {

		System.out.println("\nMain Program: aroundAdviceDemo");
		System.out.println("Calling getFortune()");

		String data = trafficFortuneService.getFortune();

		System.out.println("\nMy fortune is: " + data);
	}

	private void afterAdviceDemo(AccountDAO accountDAO) {

		List<Account> accounts = null;

		try {
			boolean tripWire = false; // no exception
			accounts = accountDAO.findAccounts(false); // no exception
		}
		catch (Exception exc) {
			System.out.println("\nMain Program: ... caught exception: " + exc);
		}

		System.out.println("\nMain Program: afterAdviceDemo");
		System.out.println("----");
		System.out.println("Accounts: " + accounts);
		System.out.println("\n");
	}

	private void afterThrowingAdviceDemo(AccountDAO accountDAO) {
		List<Account> accounts = null;

		try {
			accounts = accountDAO.findAccounts(true);
		} catch (Exception e) {
			System.out.println("\nMain Program: " + e);
		}

		System.out.println("\nMain Program: afterThrowingAdviceDemo");
		System.out.println("----");
		System.out.println("Accounts: " + accounts);
		System.out.println("\n");
	}

	private void afterReturningAdviceDemo(AccountDAO accountDAO) {

		List<Account> accounts = accountDAO.findAccounts();

		System.out.println("\nMain Program: afterReturningAdviceDemo");
		System.out.println("----");
		System.out.println("Accounts: " + accounts);
		System.out.println("\n");
	}

	private void beforeAdviceDemo(AccountDAO accountDAO, MembershipDAO membershipDAO) {

		Account account = new Account();
		account.setName("XYZ");
		account.setLevel("Platinum");
		accountDAO.addAccount(account, true);
		boolean done = accountDAO.doSomething();
		String type = accountDAO.getType();
		String code = accountDAO.getCode();
		accountDAO.setCode(code + "ES0123456789");

		membershipDAO.addAccount();
	}
}
