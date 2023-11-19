package shop.springbootapp.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendMail(String recipientAddress, String text, String subject, boolean isHtml) throws MessagingException;

}
