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

    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException, jakarta.mail.MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, "utf-8");
        String htmlContent = "<h1>This is a test Spring Boot email</h1>" +
                "<p>It can contain <strong>HTML</strong> content.</p>";
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        helper.setTo(to);
        FileSystemResource file = new FileSystemResource(new File("test.jpg"));
        helper.addAttachment("test.jpg", file);

        mailSender.send(msg);
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
