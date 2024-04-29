/**
 * 
 */
package com.lti.dto;

import org.springframework.stereotype.Component;

/**
 * @author Nikhil, Luca
 *
 */
@Component
public class Payment {
	private int paymentID;
	private Student payer;
	private double amountPaid;
	private String modeOfPayment; // Just added from feedback
	private String cvv;
	private String expiration;
	private String cardNumber;
	

	
	/**
	 * @return the paymentID
	 */
	public int getPaymentID() {
		return paymentID;
	}
	/**
	 * @param paymentID the paymentID to set
	 */
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	/**
	 * @return the payer
	 */
	public Student getPayer() {
		return payer;
	}
	/**
	 * @param payer the payer to set
	 */
	public void setPayer(Student payer) {
		this.payer = payer;
	}
	/**
	 * @return the amountPaid
	 */
	public double getAmountPaid() {
		return amountPaid;
	}
	/**
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	/**
	 * @return the modeOfPayment
	 */
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	/**
	 * @param modeOfPayment the modeOfPayment to set
	 */
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
}
