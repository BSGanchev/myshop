package shop.springbootapp.service;

import jakarta.mail.MessagingException;
import shop.springbootapp.model.entity.UserActivationToken;
import shop.springbootapp.model.events.UserRegistrationEvent;

import java.util.UUID;

public interface UserActivationService {

    void userRegistered(UserRegistrationEvent event) throws MessagingException;

    void deleteObsoleteActivationToken();

    String createActivationToken(String userEmail);

    UUID getUserId(String activationLink);

    UserActivationToken getActivationToken(String token);

    void deleteUsedActivationToken(UserActivationToken activationToken);
}
