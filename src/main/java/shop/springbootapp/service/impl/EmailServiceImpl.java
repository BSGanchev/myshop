package shop.springbootapp.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import shop.springbootapp.model.service.MailPropertyAccess;
import shop.springbootapp.service.EmailService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class EmailServiceImpl implements EmailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final MailPropertyAccess mailPropertyAccess;

    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender, MailPropertyAccess mailPropertyAccess) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.mailPropertyAccess = mailPropertyAccess;
    }

    @Override
    public void sendRegistrationEmail(String email, String username) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setTo(email);
        helper.setFrom(mailPropertyAccess.getaPofBMailEmail());
        helper.setReplyTo(mailPropertyAccess.getaPofBMailEmail());
        helper.setSentDate(Date.from(Instant.now()));
        helper.setSubject("Welcome to A Piece of Beauty");
        helper.setText(generateRegistrationEmailBody(username), true);

        javaMailSender.send(mimeMessage);

    }

    private String generateRegistrationEmailBody(String username) {
        Context context = new Context();
        context.setVariable("username", username);

        return templateEngine.process("registration-email", context);
    }
}
