package com.example.eistechtest.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ApplicationConfig {

    @Autowired
    CamelContext camelContext;

}
