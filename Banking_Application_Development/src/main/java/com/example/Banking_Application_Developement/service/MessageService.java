package com.example.Banking_Application_Developement.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private JavaMailSender javaMailSender;

    public MessageService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // Message service for login
    public void loginNotification(String receiver, String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(receiver);
        messageHelper.setSubject("Login Notification");
        messageHelper.setText(message);
        javaMailSender.send(messageHelper.getMimeMessage());
    }

    // Message service for registration
    public void registrationNotification(String receiver, String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(receiver);
        messageHelper.setSubject("Registration Notification");
        messageHelper.setText(message);
    }

}
