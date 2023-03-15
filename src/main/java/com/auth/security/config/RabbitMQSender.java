package com.auth.security.config;

import com.auth.security.user.modal.User;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(String user) {
        String message = "Hello World!";
        template.convertAndSend(queue.getName(), user);
        System.out.println(" [x] Sent '" + user + "'");
    }
}