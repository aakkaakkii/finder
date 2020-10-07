package com.finder.port.out;

public interface MailSenderPort {
    void sendActivationMail(String token, String to);
    void sendRestoreMail(String token, String to);
}
