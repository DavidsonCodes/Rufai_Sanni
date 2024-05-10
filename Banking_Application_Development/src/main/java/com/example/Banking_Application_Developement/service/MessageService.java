package com.example.Banking_Application_Developement.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
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
    @Async
    public void registrationNotification(String receiver, String firstName) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(receiver);
        messageHelper.setSubject("Registration Successful");
        String message = String.format("Dear %s,\nCongratulations\nYou have successfully registered with Email address: %s", firstName, receiver);
        messageHelper.setText(message);
        javaMailSender.send(messageHelper.getMimeMessage());
    }

    @Async
    public void depositNotification(String firstName, String username, double amount) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(username);
        messageHelper.setSubject("Fund Deposit Alert!");
        String message = String.format("Dear %s,\nDeposit of %s has been credited to your account number.", firstName, amount);
        messageHelper.setText(message);
        javaMailSender.send(messageHelper.getMimeMessage());
    }

    @Async
    public void withdrawalNotification(String firstName, String username, double amount) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(username);
        messageHelper.setSubject("Fund Debit Alert!");
        String message = String.format("Dear %s,\nA withdrawal of %s has been debited from your account number.", firstName, amount);
        messageHelper.setText(message);
        javaMailSender.send(messageHelper.getMimeMessage());
    }

    @Async
    public void sendResetCode(String username, String code) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(username);
        messageHelper.setSubject("Password reset code");
        String message = String.format("Dear %s,\nHere is your password reset code: %s", username, code);
        messageHelper.setText(message);
        javaMailSender.send(messageHelper.getMimeMessage());
    }
}
