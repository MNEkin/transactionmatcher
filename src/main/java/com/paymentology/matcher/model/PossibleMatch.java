package com.paymentology.matcher.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class PossibleMatch {
	
	private Transaction t1;
	private Transaction t2;
	private double matchRate;

}
