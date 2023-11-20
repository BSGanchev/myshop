package shop.springbootapp.service;

import jakarta.mail.MessagingException;
import shop.springbootapp.model.events.RegistrationEvent;

public interface UserActivationService {

    void userRegister(RegistrationEvent event) throws MessagingException;
}
