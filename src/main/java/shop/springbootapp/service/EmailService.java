package shop.springbootapp.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendRegistrationEmail(String email, String username) throws MessagingException;
}
