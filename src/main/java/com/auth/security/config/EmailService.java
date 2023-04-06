package com.auth.security.config;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, String type) throws jakarta.mail.MessagingException, FileNotFoundException {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        File attachmentFile = new File(System.getProperty("user.dir") + "/response.png");
        helper.addAttachment(attachmentFile.getName(), attachmentFile);
        helper.setSubject(subject);
        helper.setText(mailInput(type, to), true);
        helper.setTo(to);
        mailSender.send(msg);
    }

    public String mailInput(String type, String to) throws FileNotFoundException {
        Scanner scanner = new Scanner(mailType(type));
        StringBuilder sb = new StringBuilder();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("{username}")) {
                sb.append(line.replace("{username}", to));
            } else
                sb.append(line);
        }
        scanner.close();
        return sb.toString();
    }

    public File mailType(String type) {
        if (type.equals("register")) {
            return new File(System.getProperty("user.dir") + "/src/main/resources/htmlTemplates" + "/registration.html");
        }
        return new File(System.getProperty("user.dir") + "/src/main/resources/htmlTemplates" + "/signin.html");
    }
}
