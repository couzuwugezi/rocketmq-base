package com.mq.rocketmq.base.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/**
 * 消息的接受者
 */
public class Consumer {

    public static void main(String[] args) throws Exception {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer1");
        consumer.setNamesrvAddr("192.168.67.128:9876;192.168.67.130:9876");
        consumer.subscribe("topic*", "tag1");
        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            list.forEach(item->{
                byte[] body = item.getBody();
                String string = new String(body);
                System.out.println(string);
            });
            System.out.println(list);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }
}
