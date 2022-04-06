package com.paymentology.matcher.service;

import org.apache.commons.text.similarity.JaroWinklerSimilarity;
import org.springframework.stereotype.Component;

@Component
public class JaroWinklerMatchChecker implements SimilarityChecker {

	@Override
	public Double apply(String left, String right) {
		JaroWinklerSimilarity similarity = new JaroWinklerSimilarity();
		
		return similarity.apply(left, right);
	}

	

}
