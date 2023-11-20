package shop.springbootapp.model.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailPropertyAccess {

    @Value("${apofb.mail.host}")
    private String aPofBMailHost;
    @Value("${apofb.mail.port}")
    private Integer aPofBMailPort;
    @Value("${apofb.mail.username}")
    private String aPofBMailUsername;
    @Value("${apofb.mail.password}")
    private String aPofBMailPassword;
    @Value("${apofb.mail.from-title}")
    private String aPofBMailFrom;
    @Value("${apofb.mail.from-email}")
    private String aPofBMailEmail;
    @Value("${apofb.mail.transport-protocol}")
    private String aPofBMailTransportProtocol;

    @Value("${apofb.mail.smtp-auth}")
    private Boolean aPofBMailSmtpAuth;

    @Value("${apofb.mail.smtp-starttls-enable}")
    private Boolean aPofBMailSmtpStarttlsEnable;

    @Value("${apofb.mail.smtp-ssl-enable}")
    private Boolean aPofBMailSmtpSslEnable;

    @Value("${apofb.mail.debug}")
    private Boolean aPofBMailDebug;

    @Value("${apofb.mail.send-emails}")
    private Boolean aPofBMailSendEmails;

    public String getaPofBMailHost() {
        return aPofBMailHost;
    }

    public Integer getaPofBMailPort() {
        return aPofBMailPort;
    }

    public String getaPofBMailUsername() {
        return aPofBMailUsername;
    }

    public String getaPofBMailPassword() {
        return aPofBMailPassword;
    }

    public String getaPofBMailFrom() {
        return aPofBMailFrom;
    }

    public String getaPofBMailEmail() {
        return aPofBMailEmail;
    }

    public String getaPofBMailTransportProtocol() {
        return aPofBMailTransportProtocol;
    }

    public Boolean getaPofBMailSmtpAuth() {
        return aPofBMailSmtpAuth;
    }

    public Boolean getaPofBMailSmtpStarttlsEnable() {
        return aPofBMailSmtpStarttlsEnable;
    }

    public Boolean getaPofBMailSmtpSslEnable() {
        return aPofBMailSmtpSslEnable;
    }

    public Boolean getaPofBMailDebug() {
        return aPofBMailDebug;
    }

    public Boolean getaPofBMailSendEmails() {
        return aPofBMailSendEmails;
    }
}
