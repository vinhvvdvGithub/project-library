package com.practice.projectlibrary.service;

public interface IMailService {
    void send(String to, String subject);

    String buildEmail(String name, String link);
}
