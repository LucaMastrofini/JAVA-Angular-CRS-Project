package com.lti.bean;

import org.springframework.stereotype.Component;

@Component
public class Notification {
	private int notificationID;
	private String message;
	/**
	 * @return the notificationID
	 */
	public int getNotificationID() {
		return notificationID;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
