package com.arthi.rabbitmq.hello.rabbitr.component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello-spring-rabbit")
public class Receiver {

    public void receiveMessage(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());
        System.out.println("Received <" + message + "> at " + formatter.format(Instant.now()));
    }

}
