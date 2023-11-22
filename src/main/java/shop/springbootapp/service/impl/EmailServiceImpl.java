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
import java.util.Date;

@Service
public class EmailServiceImpl implements EmailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final MailPropertyAccess mailPropertyAccess;
    private final static String EMAIL_TEMPLATE_LOCATION = "email/registration-email";

    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender, MailPropertyAccess mailPropertyAccess) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.mailPropertyAccess = mailPropertyAccess;
    }

    @Override
    public void sendRegistrationEmail(String email, String username, String activationLink) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);



        helper.setTo(email);
        helper.setFrom(mailPropertyAccess.getaPofBMailEmail());
        helper.setReplyTo(mailPropertyAccess.getaPofBMailEmail());
        helper.setSentDate(Date.from(Instant.now()));
        helper.setSubject(mailPropertyAccess.getaPofBMailFrom());
        helper.setText(generateRegistrationEmailBody(username, activationLink), true);

        javaMailSender.send(mimeMessage);
    }

    private String generateRegistrationEmailBody(String username, String activationLink) {
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("activationLink", activationLink);


        return templateEngine.process(EMAIL_TEMPLATE_LOCATION, context);
    }
}
