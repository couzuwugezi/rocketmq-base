package com.mq.rocketmq.base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * 发送异步消息
 */
public class AsyncProducer {

    public static void main(String[] args) throws Exception {
        // 创建一个默认的生产者
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("producer2");
        // 指定nameserver，选择一个就行了，这里全写上去
        defaultMQProducer.setNamesrvAddr("192.168.67.128:9876;192.168.67.130:9876");
        // 启动
        defaultMQProducer.start();
        defaultMQProducer.setRetryTimesWhenSendAsyncFailed(0);
        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setTopic("base");
            message.setBody(("Hello World" + i).getBytes());
            message.setTags("tag3");
            message.setKeys("key");

            defaultMQProducer.send(message, new SendCallback() {

                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送结果：" + sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("发送异常：" + throwable);
                }
            });

            TimeUnit.SECONDS.sleep(1);

        }
        // 关闭生产者
        defaultMQProducer.shutdown();
    }
}
