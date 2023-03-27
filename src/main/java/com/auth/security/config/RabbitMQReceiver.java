package com.auth.security.config;

import com.auth.security.user.modal.User;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import com.auth.security.config.EmailService;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@RequiredArgsConstructor
@Service
public class RabbitMQReceiver {
    private static Logger logger = LogManager.getLogger(RabbitMQReceiver.class.toString());
    private final EmailService emailService;
    @RabbitListener(queues = {"${rabbitmq.json}"})
    public void receiver(User user) throws MessagingException, jakarta.mail.MessagingException {
        logger.info("New user registered" + user.toString());
        emailService.sendSimpleMessage(user.getEmail(), "Registration", "Success");
    }
}