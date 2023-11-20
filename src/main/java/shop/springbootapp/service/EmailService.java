package shop.springbootapp.service;

public interface EmailService {
    void sendRegistrationEmail(String email, String username);
}
