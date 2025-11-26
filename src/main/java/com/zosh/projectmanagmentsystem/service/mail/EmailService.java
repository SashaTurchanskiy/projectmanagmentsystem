package com.zosh.projectmanagmentsystem.service.mail;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;


public interface EmailService {

    void sendEmailWithToken(String userEmail, String link) throws MessagingException;
}
