package com.lti.service;

import org.springframework.stereotype.Service;

import com.lti.dto.Payment;

@Service
public class PaymentService implements PaymentServiceInterface {

	Payment payment;
	public PaymentService() {
		payment = new Payment();
	}
	public void validatePayment() {
		//validates payment from student
	}
}
