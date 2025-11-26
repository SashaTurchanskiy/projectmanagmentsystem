package com.zosh.projectmanagmentsystem.service.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmailWithToken(String userEmail, String link) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String subject = "Join perfect team invitation";
        String text = "Click the link to join the team: " + link;

        helper.setSubject(subject);
        helper.setText(text, text);
        helper.setTo(userEmail);

        try {
            javaMailSender.send(mimeMessage);
        }
        catch (MailSendException e){
            throw new MailSendException("Failed to send email to " + userEmail, e);
        }


    }
}
