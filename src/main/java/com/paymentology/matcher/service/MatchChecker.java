package com.paymentology.matcher.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paymentology.matcher.config.MatcherConfig;
import com.paymentology.matcher.model.PossibleMatch;
import com.paymentology.matcher.model.Report;
import com.paymentology.matcher.model.Transaction;
import com.paymentology.matcher.service.factory.SimilarityCheckerFactory;

@Component
public class MatchChecker {

	Report matchReport;
	@Autowired
	MatcherConfig matcherConfig;
	@Autowired
	SimilarityCheckerFactory similarityCheckerFactory;

	public void checkPossibleMatch(List<Transaction> l1, List<Transaction> l2, String algorithm) {
		System.out.println(l1.size() + ":" + l2.size());
		double matchRate;
		Transaction tmp;
		double tmpRate;
		SimilarityChecker similarityChecker = similarityCheckerFactory.getCheckerInstance(algorithm);
		for (Transaction t1 : l1) {
			matchRate = 0.0;
			tmp = null;
			tmpRate = 0.0;
			for (Transaction t2 : l2) {
				try {
					tmpRate = NumberFormat.getInstance().parse(
							new DecimalFormat("##.##").format(similarityChecker.apply(t1.toString(), t2.toString())))
							.doubleValue();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					tmpRate = 0.0;
				}

				if (matchRate < tmpRate) {

					matchRate = tmpRate;
					tmp = t2;
				}
			}
			l2.remove(tmp);
			addPossibleMatch(t1, tmp, matchRate);
		}
		addLeftOvers(l2, matchReport);
	}

	public Report checkTransactionMatch(List<Transaction> l1, List<Transaction> l2, String algorithm) {
		List<Transaction> matchedTransactions = new ArrayList<Transaction>();
		matchReport = new Report();
		for (Transaction t : l1) {
			if (l2.contains(t)) {
				matchedTransactions.add(t);
				l2.remove(t);
			}
		}
		l1.removeAll(matchedTransactions);

		matchReport.setMatchedRecord(matchedTransactions.size());
		matchReport.setTotalRecords1(matchedTransactions.size() + l1.size());
		matchReport.setTotalRecords2(matchedTransactions.size() + l2.size());
		matchReport.setUnmatchedRecord1(l1.size());
		matchReport.setUnmatchedRecord2(l2.size());

		checkPossibleMatch(l1, l2, algorithm);

		return matchReport;
	}

	public void addLeftOvers(List<Transaction> transactionList, Report matchReport) {
		for (Transaction t : transactionList) {
			matchReport.getPossibleMatchList().add(new PossibleMatch(null, t, 0.0));
		}
	}

	public void addPossibleMatch(Transaction t1, Transaction t2, double matchRate) {

		matchReport.getPossibleMatchList().add(
				new PossibleMatch(t1, t2, matchRate * 100 >= matcherConfig.getTreshold() ? (matchRate * 100) : 0.0));

	}

}
