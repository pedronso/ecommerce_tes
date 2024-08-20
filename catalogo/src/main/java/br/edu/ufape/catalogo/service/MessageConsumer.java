package br.edu.ufape.catalogo.service;

import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Service
public class MessageConsumer {

    @RabbitListener(queues = "preco_queue")
    public void receiveMessage(Object message) {
        // Process the received message
        System.out.println("Received message: " + message);
    }
}