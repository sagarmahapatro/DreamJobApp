package com.dreamjob.Jobopening.listener;

import org.springframework.kafka.annotation.KafkaListener;

public class JobOpeningListener {

    @KafkaListener(topics = "topicName", groupId = "foo")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}