package com.lti.service;

import com.lti.bean.Payment;

public class PaymentService implements PaymentServiceInterface {

	Payment payment;
	public PaymentService() {
		payment = new Payment();
	}
	public void validatePayment() {
		//validates payment from student
	}
}
