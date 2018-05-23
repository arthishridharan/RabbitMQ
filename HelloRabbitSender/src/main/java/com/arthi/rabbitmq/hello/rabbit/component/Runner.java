package com.arthi.rabbitmq.hello.rabbit.component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.arthi.rabbitmq.hello.rabbit.HelloRabbitApplication;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").withLocale(Locale.getDefault())
            .withZone(ZoneId.systemDefault());

    public Runner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        IntStream.rangeClosed(1, 10).forEach(i -> sendMessage(i));
    }

    private void sendMessage(int i) {
        System.out.println("Sending message <" + i + "> at " + formatter.format(Instant.now()) + " ...");
        rabbitTemplate.convertAndSend(HelloRabbitApplication.topicExchangeName, "arthi.com.rabbit", "<" + i + "> Hello from RabbitMQ!");
        System.out.println("Sent message at " + formatter.format(Instant.now()) + "!\n\n");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
