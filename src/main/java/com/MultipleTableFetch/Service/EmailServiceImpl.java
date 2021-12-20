package com.MultipleTableFetch.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    SimpleMailMessage preConfiguredMessage;

    static final String MAIL_ADDRESS = "no-reply@prince";

    @Override
    public String sendMailMessage(String subject, String text, String email) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(text);
            mailMessage.setFrom(MAIL_ADDRESS);
            emailSender.send(mailMessage);
            return "send";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendPreConfiguredMail(String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        emailSender.send(mailMessage);
    }

}
