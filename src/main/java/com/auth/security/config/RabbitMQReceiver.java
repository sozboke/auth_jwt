package com.auth.security.config;

import com.auth.security.user.modal.User;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import com.auth.security.config.EmailService;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

@RequiredArgsConstructor
@Service
public class RabbitMQReceiver {
    private static Logger logger = LogManager.getLogger(RabbitMQReceiver.class.toString());
    private final EmailService emailService;
    @RabbitListener(queues = {"${rabbitmq.register}"})
    public void registerListener(User user) throws jakarta.mail.MessagingException, FileNotFoundException {
        logger.info("New user registered" + user.toString());
        emailService.sendSimpleMessage(user.getEmail(), "Registration", "register");
    }

    @RabbitListener(queues = {"${rabbitmq.signup}"})
    public void signupListener(User user) throws jakarta.mail.MessagingException, FileNotFoundException {
        logger.info("New user registered" + user.toString());
        emailService.sendSimpleMessage(user.getEmail(), "Authentication", "signup");
    }
}