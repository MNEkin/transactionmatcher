package com.paymentology.matcher.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Transaction {

	private String ProfileName;
	private String TransactionDate;
	private String TransactionAmount;
	private String TransactionNarrative;
	private String TransactionDescription;
	private String TransactionID;
	private int    TransactionType;
	private String WalletReference;

	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != getClass()) {
			return false;
		} else {
			Transaction t = (Transaction) o;
			if (t.getProfileName().equals(this.getProfileName())
					&& t.getTransactionAmount().equals(this.getTransactionAmount())
					&& t.getTransactionDate().equals(this.getTransactionDate())
					&& t.getTransactionDescription().equals(this.getTransactionDescription())
					&& t.getTransactionID().equals(this.getTransactionID())
					&& t.getTransactionNarrative().equals(this.getTransactionNarrative())
					&& t.getWalletReference().equals(this.getWalletReference())
					&& t.getTransactionType() == this.getTransactionType()) {

				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public String toString() {
		return getProfileName() + "," + getTransactionDate() + "," + getTransactionAmount() + ","
				+ getTransactionNarrative() + "," + getTransactionDescription() + "," + getTransactionID() + ","
				+ getTransactionType() + "," + getWalletReference();
	}

	public Transaction(String line) {
		System.out.println(line);
		String[] tmp = line.split(",", -1);

		this.setProfileName(tmp[0]);
		this.setTransactionDate(tmp[1]);
		this.setTransactionAmount(tmp[2]);
		this.setTransactionNarrative(tmp[3]);
		this.setTransactionDescription(tmp[4]);
		this.setTransactionID(tmp[5]);
		this.setTransactionType(Integer.valueOf(tmp[6]));
		this.setWalletReference(tmp[7]);
	}

}
