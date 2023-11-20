package shop.springbootapp.service.impl;

import jakarta.mail.MessagingException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import shop.springbootapp.model.events.RegistrationEvent;
import shop.springbootapp.service.EmailService;
import shop.springbootapp.service.UserActivationService;
@Service
public class UserActivationServiceImpl implements UserActivationService {
    private final EmailService emailService;

    public UserActivationServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    @EventListener(RegistrationEvent.class)
    public void userRegister(RegistrationEvent event) throws MessagingException {
        this.emailService.sendMail(event.getUserEmail(), "Welcome user", "Activation email", true);

        System.out.println(event.getUserEmail());

    }
}
