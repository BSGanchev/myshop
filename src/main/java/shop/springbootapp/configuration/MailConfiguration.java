package shop.springbootapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import shop.springbootapp.model.service.MailPropertyAccess;

import java.util.Objects;
import java.util.Properties;

@Configuration
public class MailConfiguration {
    private final MailPropertyAccess mailPropertyAccess;

    public MailConfiguration(MailPropertyAccess mailPropertyAccess) {
        this.mailPropertyAccess = mailPropertyAccess;
    }

    @Bean
    public JavaMailSender javaMailSender() {

        JavaMailSenderImpl javaMailSender = getJavaMailSender();
        Properties properties = javaMailSender.getJavaMailProperties();

        String transportProtocol = this.mailPropertyAccess.getaPofBMailTransportProtocol();
        if (Objects.nonNull(transportProtocol)) {
            properties.put("mail.transport.protocol", transportProtocol);
        }
        Boolean smtpAuth = this.mailPropertyAccess.getaPofBMailSmtpAuth();
        if (Objects.nonNull(smtpAuth)) {
            properties.put("mail.smtp.auth", smtpAuth);
        }
        Boolean smtpStartEnable = this.mailPropertyAccess.getaPofBMailSmtpStarttlsEnable();
        if (Objects.nonNull(smtpStartEnable)) {
            properties.put("mail.smtp.starttls", smtpStartEnable);
        }
        Boolean smtpSslEnable = this.mailPropertyAccess.getaPofBMailSmtpSslEnable();
        if (Objects.nonNull(smtpSslEnable)) {
            properties.put("mail.smtp.ssl.enable", smtpSslEnable);
        }
        Boolean enableDebug = this.mailPropertyAccess.getaPofBMailDebug();
        if (Objects.nonNull(enableDebug)) {
            properties.put("mail.smtp.debug", enableDebug);
        }

        return javaMailSender;
    }
    private JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        String mailHost = this.mailPropertyAccess.getaPofBMailHost();
        if (Objects.nonNull(mailHost)) {
            mailSender.setHost(mailHost);
        }

        Integer mailPort = this.mailPropertyAccess.getaPofBMailPort();
        if (Objects.nonNull(mailHost)) {
            mailSender.setPort(mailPort);
        }

        String mailUsername = this.mailPropertyAccess.getaPofBMailUsername();
        if (Objects.nonNull(mailUsername)) {
            mailSender.setUsername(mailUsername);
        }

        String mailPassword = this.mailPropertyAccess.getaPofBMailPassword();
        if (Objects.nonNull(mailPassword)) {
            mailSender.setPassword(mailPassword);
        }
        return mailSender;
    }
}
