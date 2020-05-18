package com.example.eistechtest.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class WordsKafkaConsumer {

    @Autowired
    WordsBuffer wordsBuffer;

    public void consume(List<String> words) {
        wordsBuffer.addAll(words);
        log.info("WordsKafkaConsumer: consume [words: {}]", words);
    }

}
