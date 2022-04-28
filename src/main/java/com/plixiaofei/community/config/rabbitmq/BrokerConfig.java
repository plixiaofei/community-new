package com.plixiaofei.community.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2022/4/25 by plixiaofei
 */
@Configuration
public class BrokerConfig {
    @Value("${rabbitmq.notify.exchange}")
    private String EXCHANGE_NOTIFY;

    @Value("${rabbitmq.notify.queue.at}")
    private String QUEUE_AT;

    @Value("${rabbitmq.notify.queue.reply}")
    private String QUEUE_REPLY;

    @Bean
    public DirectExchange exchangeNotify() {
        return new DirectExchange(EXCHANGE_NOTIFY, true, false);
    }
    @Bean
    public Queue queueAt() {
        return new Queue(QUEUE_AT, true, false, false);
    }
    @Bean
    public Queue queueReply() {
        return new Queue(QUEUE_REPLY, true, false, false);
    }
    @Bean
    public Binding bindAt() {
        return BindingBuilder
                .bind(queueAt())
                .to(exchangeNotify())
                .with("at");
    }

    @Bean
    public Binding bindReply() {
        return BindingBuilder
                .bind(queueReply())
                .to(exchangeNotify())
                .with("reply");
    }
}
