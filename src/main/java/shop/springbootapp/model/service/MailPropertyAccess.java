package shop.springbootapp.model.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailPropertyAccess {

    @Value("${apofb.mail.host:#{null}")
    private String aPofBMailHost;
    @Value("${apofb.mail.port:#{null}}")
    private Integer aPofBMailPort;
    @Value("${apofb.mail.username:#{null}}")
    private String aPofBMailUsername;
    @Value("${apofb.mail.password:#{null}}")
    private String aPofBMailPassword;
    @Value("${apofb.mail.from-title:#{null}}")
    private String aPofBMailFrom;
    @Value("${apofb.mail.from-email:#{null}}")
    private String aPofBMailEmail;
    @Value("${apofb.mail.transport-protocol:#{null}}")
    private String aPofBMailTransportProtocol;

    @Value("${apofb.mail.smtp-auth:#{null}}")
    private Boolean aPofBMailSmtpAuth;

    @Value("${apofb.mail.smtp-starttls-enable:#{null}}")
    private Boolean aPofBMailSmtpStarttlsEnable;

    @Value("${apofb.mail.smtp-ssl-enable:#{null}}")
    private Boolean aPofBMailSmtpSslEnable;

    @Value("${apofb.mail.debug:#{false}}")
    private Boolean aPofBMailDebug;

    @Value("${apofb.mail.send-emails:#{false}}")
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
