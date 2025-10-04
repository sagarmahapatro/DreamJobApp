package com.dreamjob.event;

import com.dreamjob.event.EventConfigProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class EventConfigAutoConfiguration {

    public EventConfigAutoConfiguration () {
        System.out.println("EventConfigAutoConfiguration created >>>>>>>>>>>>>>>>>>>>>>>>>> ");
    }

    @Bean(name = "eventConfigProperties")
    @ConfigurationProperties(prefix = "event")
    public EventConfigProperties eventConfigProperties() {
        return new EventConfigProperties();
    }

}
