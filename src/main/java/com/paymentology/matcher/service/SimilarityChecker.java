package com.paymentology.matcher.service;

public interface SimilarityChecker {

	public Double apply(String left, String right);
}
