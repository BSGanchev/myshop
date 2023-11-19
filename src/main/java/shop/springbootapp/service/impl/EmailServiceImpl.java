package shop.springbootapp.service.impl;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import shop.springbootapp.service.EmailService;
@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendMail(String recipientAddress, String text,
                         String subject, boolean isHtml) throws MessagingException {



    }
}
