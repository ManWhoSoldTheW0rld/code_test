package com.mortgage.plan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mortgage.plan.common.service")
public class MortgagePlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(MortgagePlanApplication.class, args);
	}
}
