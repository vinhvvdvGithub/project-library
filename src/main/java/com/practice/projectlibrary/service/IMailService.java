package com.practice.projectlibrary.service;

import com.practice.projectlibrary.entity.EmailDetails;

public interface IMailService {
	void send(String to, String subject);

	String buildEmail(String name, String link);

	String templateEmailNotification(String name);

	String sendEmailNotification(EmailDetails emailDetails);

	String sendEmailNotificationWithAttachment(EmailDetails emailDetails);

}
