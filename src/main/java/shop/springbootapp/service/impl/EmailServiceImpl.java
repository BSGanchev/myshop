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

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final MailPropertyAccess mailPropertyAccess;
    private final TemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender javaMailSender, MailPropertyAccess mailPropertyAccess, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.mailPropertyAccess = mailPropertyAccess;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendMail(String recipientAddress, String text,
                         String subject, boolean isHtml) throws MessagingException {
        if(isDisabled()) {
            return;
        }

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom(mailPropertyAccess.getaPofBMailFrom());
        mimeMessageHelper.setReplyTo(mailPropertyAccess.getaPofBMailEmail());
        mimeMessageHelper.setTo(recipientAddress);
        mimeMessageHelper.setText(generateRegistrationEmailBody(subject), isHtml);
        mimeMessageHelper.setSubject(subject);

        javaMailSender.send(mimeMessage);

    }

    private String generateRegistrationEmailBody(String userName) {

        Context context = new Context();
        context.setVariable("username", userName);
        return templateEngine.process("registration-email", context);
    }

    private boolean isDisabled() {
        Boolean enabled = this.mailPropertyAccess.getaPofBMailSendEmails();

        return Objects.nonNull(enabled) && enabled;
    }
}
