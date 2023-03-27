package com.auth.security.config;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    Properties properties=new Properties();
    Session session=Session.getDefaultInstance(properties);


//
//    @Autowired
//    private ResourceLoader resourceLoader;
    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException, jakarta.mail.MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();


        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(msg, true);
        mimeMessageHelper.setTo("beyzaa0204@gmail.com");
        mimeMessageHelper.setText("<h1> checkt attttt</h1>");
//        Resource file = resourceLoader.getResource("test.jpg");
//
//        mimeMessageHelper.addAttachment("test.jpg",file);
//        mailSender.send(msg);


//        Message message = new MimeMessage();
//        message.setText(text);
//        message.setFrom(new InternetAddress("beyzaa0204@gmail.com"));
//        mailSender.send(message);

//        message.setTo(to);
//        message.setSubject(subject);
//        message.setC
//        message.setText("<a href=\"www.google.com\">www.google.com</a>");
//        mailSender.send(message);
    }
}
