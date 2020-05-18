package com.example.eistechtest.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Handler;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SentenceKafkaProducer {

    @Autowired
    WordsBuffer wordsBuffer;

    @Autowired
    private ProducerTemplate producer;

    @Handler
    public void produce() {
        final List<WordDelayed> wordDelayedList = new ArrayList<>();
        wordsBuffer.poll(wordDelayedList);
        final String sentence = wordDelayedList.stream().map(wd -> wd.getWord()).collect(Collectors.joining(" "));

        producer.sendBody("kafka:{{sentence.kafka.topic.name}}?brokers={{kafka.bootstrap.servers}}",
                Instant.now() + sentence);
//                sentence);
        log.info("Produce sentence: [sentence: {}]", sentence);
    }
}
