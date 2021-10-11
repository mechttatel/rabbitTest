package com.example.rabbittest;

import com.example.rabbittest.tut1.RabbitAmqpTutorialRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitTestApplication {

    @Profile("usage_message")
    @Bean
    public CommandLineRunner usage() {
        return args -> System.out.println("Работаем по профилям");
    }

    @Profile("!usage_message")
    @Bean
    public CommandLineRunner tutorial() {
        return new RabbitAmqpTutorialRunner();
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitTestApplication.class, args);
    }

}
