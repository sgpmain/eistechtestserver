package com.example.eistechtest.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.DelayQueue;

import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Component
@Slf4j
public class WordsBuffer {

    private DelayQueue<WordDelayed> delayQueue = new DelayQueue<>();

    public boolean addAll(Collection<String> words) {
        final Collection<String> wordCollection = emptyIfNull(words);
        final Set<WordDelayed> delayedSet = wordCollection.stream()
                .map(WordDelayed::new)
                .collect(toSet());
        final boolean isAdded = delayQueue.addAll(delayedSet);
        log.info("Words to buffer, [words: {} , isAdded: {}]",
                Arrays.toString(wordCollection.toArray()), isAdded);
        return isAdded;
    }

    public void poll(List<WordDelayed> wordDelayedList) {
        delayQueue.drainTo(wordDelayedList);
        log.info("From buffer [words: {}]", wordDelayedList);
    }
}
