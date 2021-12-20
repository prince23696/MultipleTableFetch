package com.MultipleTableFetch.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    /*  @Autowired
      private JavaMailSender emailSender;
      @Autowired
      SimpleMailMessage preConfiguredMessage;

      static final String MAIL_ADDRESS = "no-reply@prince";
  */
    @Override
    public String sendMailMessage(String subject, String text, String email) {

       /* try {
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
        }*/
        String from = "prince.gupta@oodles.io";
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES " + properties);
        properties.put("mail.smtp.host", "smtp.oodles.io");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("prince.gupta@oodles.io", "");
            }


        });
        session.setDebug(true);
        MimeMessage m = new MimeMessage(session);
        try {
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            m.setSubject(subject);
            m.setText(text);
            Transport.send(m);
            System.out.println("Sent success...................");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Email Sent Success";
    }
/*
    public void sendPreConfiguredMail(String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        emailSender.send(mailMessage);
    }*/
}