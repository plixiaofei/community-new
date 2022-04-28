package com.plixiaofei.community.component;

import com.plixiaofei.community.config.rabbitmq.BrokerConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created on 2022/4/25 by plixiaofei
 */
@Component
public class Producer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.notify.exchange}")
    private String EXCHANGE_NOTIFY;

    public void sendAt(String msg) {
        amqpTemplate.convertAndSend(EXCHANGE_NOTIFY, "at", msg);
    }

    public void sendAt(Map<String, Object> msg) {
        amqpTemplate.convertAndSend(EXCHANGE_NOTIFY, "at", msg);
    }

    public void sendReply(Map<String, Object> msg) {
        amqpTemplate.convertAndSend(EXCHANGE_NOTIFY, "reply", msg);
    }
}
