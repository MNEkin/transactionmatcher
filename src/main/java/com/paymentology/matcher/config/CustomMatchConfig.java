package com.paymentology.matcher.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("weighted")
@Getter
@Setter
public class CustomMatchConfig {

	private int transactionNarrative;
	private int transactionAmount;
	private int transactionID;
	private int transactionType;
	private int walletReference;
	private int profileName;
	private int transactionDescription;
	private int transactionDate;

}
