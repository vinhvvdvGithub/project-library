package com.practice.projectlibrary.service;

import com.practice.projectlibrary.entity.EmailDetails;

public interface IMailService {


	String emailVerifyAccount(String name, String link);

	String templateEmailNotification(String name);

	void send(String to, String subject);


	String sendEmailNotification(EmailDetails emailDetails);

	String sendEmailNotificationWithAttachment(EmailDetails emailDetails);

}
