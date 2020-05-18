package com.example.eistechtest.router;

import com.example.eistechtest.processor.SentenceKafkaProducer;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.example.eistechtest.configuration.Constants.MILLISECONDES;

@Component
public class SentenceKafkaRouter extends RouteBuilder {

    @Value("${delay.seconds.to.form.sentence}")
    private long delay;

    @Override
    public void configure() {
        from("timer://foo?fixedRate=true&period=" + delay * MILLISECONDES)
                .bean(SentenceKafkaProducer.class);
    }
}
