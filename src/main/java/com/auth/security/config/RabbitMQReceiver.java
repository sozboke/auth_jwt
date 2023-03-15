package com.auth.security.config;

import com.auth.security.user.modal.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceiver {
    private static Logger logger = LogManager.getLogger(RabbitMQReceiver.class.toString());
    @RabbitListener(queues = {"${rabbitmq.json}"})
    public void receiver(User user) {
        System.out.println("receiver part");
        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + user.toString());
    }
}