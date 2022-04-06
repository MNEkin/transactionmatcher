package com.paymentology.matcher.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Component
public class Report {
	
	private int matchedRecord;
	private int unmatchedRecord1;
	private int unmatchedRecord2;
	private int totalRecords1;
	private int totalRecords2;
	private List<PossibleMatch> possibleMatchList = new ArrayList<PossibleMatch>();
	private String error;
	

}
