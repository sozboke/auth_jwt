package com.auth.security.config;

import com.auth.security.user.modal.User;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing_register_key}")
    private String routingRegisterKey;

    @Value("${rabbitmq.routing_signup_key}")
    private String routingSignupKey;

    private RabbitTemplate rabbitTemplate;

    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendRegisterMessage(User user) {
        System.out.println("aaaaaaa");
        rabbitTemplate.convertAndSend(exchange, routingRegisterKey, user);
    }

    public void sendSignupMessage(User user) {
        System.out.println("bbbbbb");
        rabbitTemplate.convertAndSend(exchange, routingSignupKey, user);
    }
}