package shop.springbootapp.service;

import jakarta.mail.MessagingException;
import shop.springbootapp.model.events.UserRegistrationEvent;

public interface UserActivationService {

    void userRegistered(UserRegistrationEvent event) throws MessagingException;
}
