package com.example.rabbittest.tut2;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "hello")
public class Tut2Receiver {

    private final int instance;

    public Tut2Receiver(int instance) {
        this.instance = instance;
    }

    @RabbitHandler
    public void receive(String in) {
        StopWatch watch = new StopWatch();
        watch.start();

        System.out.println("Получатель " + instance + " получил сообщение " + in);
        doWork(in);
        watch.stop();

        System.out.println("Получатель " + instance + " завершил работу за " + watch.getTotalTimeSeconds());
    }

    private void doWork(String in) {
        in.chars().filter(c -> c == '.').forEach(c -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
