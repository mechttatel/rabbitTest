package com.example.rabbittest.tut2;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Tut2Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    private final AtomicInteger dots = new AtomicInteger(0);
    private final AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Hello");
        if (dots.incrementAndGet() == 4) {
            dots.set(1);
        }

        IntStream.range(0, dots.get()).forEach(i -> builder.append('.'));

        builder.append(count.incrementAndGet());
        String message = builder.toString();

        template.convertAndSend(queue.getName(), message);
        System.out.println("Sent message = " + message);
    }
}
