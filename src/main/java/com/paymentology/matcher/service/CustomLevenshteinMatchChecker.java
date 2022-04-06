package com.paymentology.matcher.service;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paymentology.matcher.config.CustomMatchConfig;
import com.paymentology.matcher.model.Transaction;

@Component
public class CustomLevenshteinMatchChecker implements SimilarityChecker {

	private LevenshteinDistance similarity = new LevenshteinDistance();
	
	CustomMatchConfig matchConfig = new CustomMatchConfig();

	@Override
	public Double apply(String left, String right) {

		double score = transactionCompareCustom(new Transaction(left), new Transaction(right));

		return score;
	}

	public Double transactionCompareCustom(Transaction t1, Transaction t2) {
		double score = 100.0;
		score = score - getSimilarityScore(t1.getProfileName(), t2.getProfileName(), matchConfig.getProfileName());
		score = score - getSimilarityScore(t1.getTransactionType(), t2.getTransactionType(),
				matchConfig.getTransactionType());
		score = score - getSimilarityScore(t1.getTransactionDate(), t2.getTransactionDate(),
				matchConfig.getTransactionDate());
		score = score - getSimilarityScore(t1.getTransactionDescription(), t2.getTransactionDescription(),
				matchConfig.getTransactionDescription());
		score = score
				- getSimilarityScore(t1.getTransactionID(), t2.getTransactionID(), matchConfig.getTransactionID());
		score = score - getSimilarityScore(t1.getTransactionNarrative(), t2.getTransactionNarrative(),
				matchConfig.getTransactionNarrative());
		score = score - getSimilarityScore(t1.getWalletReference(), t2.getWalletReference(),
				matchConfig.getWalletReference());
		score = score - getSimilarityScore(Integer.valueOf(t1.getTransactionAmount()),
				Integer.valueOf(t2.getTransactionAmount()), matchConfig.getTransactionAmount());
		return score;
	}

	private Double getSimilarityScore(String left, String right, int rate) {
		int max = left.length() < right.length() ? right.length() : left.length();
		double score = (similarity.apply(left, right) / max) * rate;
		return score;
	}

	private Double getSimilarityScore(int left, int right, int rate) {
		return left != right ? rate : 0.0;
	}

}
