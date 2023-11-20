package shop.springbootapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import shop.springbootapp.model.service.MailPropertyAccess;

import java.util.Objects;
import java.util.Properties;

@Configuration
public class EmailConfiguration {
    private final MailPropertyAccess mailPropertyAccess;

    public EmailConfiguration(MailPropertyAccess mailPropertyAccess) {
        this.mailPropertyAccess = mailPropertyAccess;
    }

    @Bean
    public JavaMailSender javaMailSender(){

        JavaMailSenderImpl javaMailSender = getJavaMailSender();

        Properties properties = javaMailSender.getJavaMailProperties();

        String transportProtocol = mailPropertyAccess.getaPofBMailTransportProtocol();
        if (Objects.nonNull(transportProtocol)) {
            properties.put("mail.transport.protocol", transportProtocol);
        }

        Boolean aPofBAuth = mailPropertyAccess.getaPofBMailSmtpAuth();
        if (Objects.nonNull(aPofBAuth)) {
            properties.put("mail.apofb.auth", aPofBAuth);
        }

        Boolean aPofBStarttlsEnable = mailPropertyAccess.getaPofBMailSmtpStarttlsEnable();
        if (Objects.nonNull(aPofBStarttlsEnable)) {
            properties.put("mail.apofb.starttls.enable", aPofBStarttlsEnable);
        }

        Boolean aPofBSslEnable = mailPropertyAccess.getaPofBMailSmtpSslEnable();
        if (Objects.nonNull(aPofBSslEnable)) {
            properties.put("mail.apofb.ssl.enable", aPofBSslEnable);
        }

        Boolean enableDebug = mailPropertyAccess.getaPofBMailDebug();
        if (Objects.nonNull(aPofBStarttlsEnable)) {
            properties.put("mail.debug", enableDebug);
        }
        return javaMailSender;
    }

    private JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        String mailHost = mailPropertyAccess.getaPofBMailHost();

        if (Objects.nonNull(mailHost)) {
            mailSender.setHost(mailHost);
        }

        Integer mailPort = mailPropertyAccess.getaPofBMailPort();

        if (Objects.nonNull(mailHost)) {
            mailSender.setPort(mailPort);
        }

        String mailUsername = mailPropertyAccess.getaPofBMailUsername();

        if (Objects.nonNull(mailUsername)) {
            mailSender.setUsername(mailUsername);
        }

        String mailPassword = mailPropertyAccess.getaPofBMailPassword();

        if (Objects.nonNull(mailPassword)) {
            mailSender.setPassword(mailPassword);
        }
        return mailSender;
    }
}
