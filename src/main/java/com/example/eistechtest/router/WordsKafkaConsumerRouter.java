package com.example.eistechtest.router;

import com.example.eistechtest.processor.WordsKafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WordsKafkaConsumerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        from("kafka:{{word.kafka.topic.name}}?brokers={{kafka.bootstrap.servers}}")
//                .bean(WordsKafkaConsumer.class);
        from("kafka:{{word.kafka.topic.name}}?brokers={{kafka.bootstrap.servers}}")
        .to("seda:input");
        from("seda:input?pollTimeout=15000")
                .bean(WordsKafkaConsumer.class);
//                to("bean:processInput").to("bean:createResponse");

    }
}
