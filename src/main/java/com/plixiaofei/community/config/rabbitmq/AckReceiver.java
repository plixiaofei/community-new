package com.plixiaofei.community.config.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Configuration;

/**
 * ACK 接受者，用于手动确认 ACK 时使用，实现 ChannelAwareMessageListener 接口
 * Created on 2022/4/25 by plixiaofei
 */
@Configuration
public class AckReceiver implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            // 将 JSON 格式数据转为实体对象
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(message);
            channel.basicAck(deliveryTag, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            channel.basicReject(deliveryTag, true);
        }
    }
}
