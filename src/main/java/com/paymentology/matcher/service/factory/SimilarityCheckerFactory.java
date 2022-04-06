package com.paymentology.matcher.service.factory;

import org.springframework.stereotype.Component;

import com.paymentology.matcher.service.CustomLevenshteinMatchChecker;
import com.paymentology.matcher.service.JaroWinklerMatchChecker;
import com.paymentology.matcher.service.SimilarityChecker;

@Component
public class SimilarityCheckerFactory {

	public SimilarityChecker getCheckerInstance(String similarityAlgorithm) {
		if (similarityAlgorithm.equals("JaroWinkler")) {
			return new JaroWinklerMatchChecker();
		} else {
			return new CustomLevenshteinMatchChecker();
		}
	}

}
