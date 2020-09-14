package com.mq.rocketmq.base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.concurrent.TimeUnit;

/**
 * 发送同步消息
 */
public class SyncProducer {

    public static void main(String[] args) throws Exception {
        // 创建一个默认的生产者
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("producer1");
        // 指定nameserver，选择一个就行了，这里全写上去
        defaultMQProducer.setNamesrvAddr("192.168.67.128:9876;192.168.67.130:9876");
        // 启动
        defaultMQProducer.start();

        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setTopic("topic1");
            message.setBody(("Hello World" + i).getBytes());
            message.setTags("tag1");
            message.setKeys("key31");

            SendResult sendResult = defaultMQProducer.send(message);
            System.out.println("发送结果：" + sendResult);
        }
        // 关闭生产者
        defaultMQProducer.shutdown();
    }
}
