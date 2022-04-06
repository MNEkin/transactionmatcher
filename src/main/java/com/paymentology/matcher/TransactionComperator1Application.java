package com.paymentology.matcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.paymentology.matcher.config.CustomMatchConfig;
import com.paymentology.matcher.config.MatcherConfig;

@SpringBootApplication
@EnableConfigurationProperties({ CustomMatchConfig.class, MatcherConfig.class})
public class TransactionComperator1Application {

	public static void main(String[] args) {
		SpringApplication.run(TransactionComperator1Application.class, args);
	}

}
