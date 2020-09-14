package com.mq.rocketmq.base.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * 发送单向消息
 */
public class OneWayProducer {

    public static void main(String[] args) throws Exception, MQBrokerException {
        // 创建一个默认的生产者
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("producer3");
        // 指定nameserver，选择一个就行了，这里全写上去
        defaultMQProducer.setNamesrvAddr("192.168.67.128:9876;192.168.67.130:9876");
        // 启动
        defaultMQProducer.start();

        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setTopic("topic3");
            message.setBody(("Hello World sendOneway" + i).getBytes());
            message.setTags("tag3");
            message.setKeys("key3");

            defaultMQProducer.sendOneway(message);
//            System.out.println("发送结果：" + sendResult);
        }
        // 关闭生产者
        defaultMQProducer.shutdown();
    }
}
