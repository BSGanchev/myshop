package shop.springbootapp.service.impl;

import jakarta.mail.MessagingException;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.springbootapp.model.entity.UserActivationToken;
import shop.springbootapp.model.events.UserRegistrationEvent;
import shop.springbootapp.repository.ActivationLinkRepository;
import shop.springbootapp.repository.UserRepository;
import shop.springbootapp.service.EmailService;
import shop.springbootapp.service.UserActivationService;
import shop.springbootapp.service.exception.ObjectNotFoundException;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class UserActivationServiceImpl implements UserActivationService {
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final ActivationLinkRepository activationTokenRepository;

    public UserActivationServiceImpl(EmailService emailService, UserRepository userRepository, ActivationLinkRepository activationTokenRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.activationTokenRepository = activationTokenRepository;
    }

    private static String generateActivationToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    @EventListener(UserRegistrationEvent.class)
    public void userRegistered(UserRegistrationEvent event) throws MessagingException {
        String activationLink = createActivationToken(event.getEmail());
        emailService.sendRegistrationEmail(event.getEmail(), event.getUsername(), activationLink);
    }
    @Transactional
    @Override
    public void deleteObsoleteActivationToken() {
        List<UserActivationToken> expiredToken = this.activationTokenRepository.findExpiredToken();
        expiredToken.forEach(token -> {
            this.userRepository.deleteById(token.getUser().getId());
            this.activationTokenRepository.deleteById(token.getId());
        });
    }

    @Override
    public String createActivationToken(String userEmail) {

        String activationToken = generateActivationToken();

        UserActivationToken userActivationToken = new UserActivationToken();
        userActivationToken.setActivationLink(activationToken);
        userActivationToken.setCreated(Instant.now());
        userActivationToken.setUser(this.userRepository.findByEmail(userEmail).orElseThrow(() -> new ObjectNotFoundException("User with email " + userEmail + " not found!")));

        this.activationTokenRepository.save(userActivationToken);

        return activationToken;
    }

    @Override
    public UUID getUserId(String activationLink) {
        return this.activationTokenRepository.getUserId(activationLink);
    }
}
