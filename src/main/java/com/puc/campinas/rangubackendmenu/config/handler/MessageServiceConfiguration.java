package com.puc.campinas.rangubackendmenu.config.handler;

import com.grcosta.messagelocator.interfaces.MessageService;
import com.grcosta.messagelocator.service.EnvironmentMessageService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageServiceConfiguration {

    @Bean
    public MessageService createMessageService(MeterRegistry meterRegistry) {
        return new EnvironmentMessageService(meterRegistry);
    }

}
