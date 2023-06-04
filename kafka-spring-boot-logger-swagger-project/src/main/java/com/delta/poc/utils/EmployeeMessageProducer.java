package com.delta.poc.utils;

   import com.delta.poc.vo.Empl;
   import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.kafka.core.KafkaTemplate;
        import org.springframework.stereotype.Component;

@Component
public class EmployeeMessageProducer {

    @Autowired
    private  KafkaTemplate<String, Empl> kafkaTemplate;
    private static final String TOPIC_NAME = "employee_topic";

//    @Autowired
//    public EmployeeMessageProducer(KafkaTemplate<String, Empl> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }

    public void sendMessage(String messageKey,Empl employee) {
        kafkaTemplate.send(TOPIC_NAME,messageKey,  employee);
    }
}
