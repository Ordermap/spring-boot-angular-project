package com.delta.poc.utils;

import com.delta.poc.vo.Empl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMessageConsumer {


    @KafkaListener(topics = "employee_topic")
    public void listen( @Payload Empl empl,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts
    ) {
       String message = String.format("Received message: %s from topic: %s, key: %s, value: %s, partition: %d, timestamp: %d",
                empl, topic, key, empl, partition, ts);
        System.out.println(message);
    }

}
