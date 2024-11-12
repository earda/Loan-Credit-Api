package com.loancredit.api.loan_credit_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class LoanCreditApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanCreditApiApplication.class, args);
	}

}
