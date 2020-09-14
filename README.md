# rocketmq-base
好久没用rocketMQ了，没想到版本更新了这么多，踩了一些新的坑，这里记录下 4.7.1

如下异常解决：不同的broker必须使用不同的store文件夹
2020-09-14 06:37:48 WARN brokerOutApi_thread_4 - registerBroker Exception, rocketmq-nameserver2:9876
org.apache.rocketmq.remoting.exception.RemotingConnectException: connect to rocketmq-nameserver2:9876 failed
	at org.apache.rocketmq.remoting.netty.NettyRemotingClient.invokeSync(NettyRemotingClient.java:394) ~[rocketmq-remoting-4.7.1.jar:4.7.1]
	at org.apache.rocketmq.broker.out.BrokerOuterAPI.registerBroker(BrokerOuterAPI.java:194) ~[rocketmq-broker-4.7.1.jar:4.7.1]
	at org.apache.rocketmq.broker.out.BrokerOuterAPI.access$000(BrokerOuterAPI.java:61) ~[rocketmq-broker-4.7.1.jar:4.7.1]
	at org.apache.rocketmq.broker.out.BrokerOuterAPI$1.run(BrokerOuterAPI.java:150) ~[rocketmq-broker-4.7.1.jar:4.7.1]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [na:1.8.0_171]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [na:1.8.0_171]
	at java.lang.Thread.run(Thread.java:748) [na:1.8.0_171]

如下异常原因：使用了异步发送的api。生产者不能连续不断的发送，在发送后线程sleep一下
org.apache.rocketmq.client.exception.MQClientException: The producer service state not OK, SHUTDOWN_ALREADY
