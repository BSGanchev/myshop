package shop.springbootapp.service.impl;

import jakarta.mail.MessagingException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import shop.springbootapp.model.events.UserRegistrationEvent;
import shop.springbootapp.service.EmailService;
import shop.springbootapp.service.UserActivationService;
@Service
public class UserActivationServiceImpl implements UserActivationService {

    private final EmailService emailService;

    public UserActivationServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    @EventListener(UserRegistrationEvent.class)
    public void userRegistered(UserRegistrationEvent event) throws MessagingException {

        emailService.sendRegistrationEmail(event.getEmail(), event.getUsername());
    }
}
