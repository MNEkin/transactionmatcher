package com.paymentology.matcher.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("matcher")
@Getter @Setter
public class MatcherConfig {
	
	private double treshold;

}
