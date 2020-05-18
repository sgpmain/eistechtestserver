package com.example.eistechtest.processor;

import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static com.example.eistechtest.configuration.Constants.MILLISECONDES;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

class WordDelayed implements Delayed {

    @Value("${delay.seconds.to.form.sentence}")
    private long delay;

    private final String word;
    private final long endTime;

    public WordDelayed(String word) {
        this.word = word;
        endTime = System.currentTimeMillis() + delay * MILLISECONDES;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long remainingTime = endTime - System.currentTimeMillis();
        return unit.convert(remainingTime, MILLISECONDS);
    }

    @Override public int compareTo(Delayed that) {
        long thisDelay = this.getDelay(MILLISECONDS);
        long thatDelay = that.getDelay(MILLISECONDS);
        return Long.compare(thisDelay, thatDelay);
    }

    String getWord() {
        return word;
    }

    @Override public String toString() {
        return "WordDelayed{" +
                "word='" + word + '\'' +
                ", endTime=" + endTime +
                '}';
    }
}
