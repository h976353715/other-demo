package com.example.other.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/**
 * @author huangqi
 * @Package com.example.other.rocketmq
 * @Description: 消费者
 * @date 2019-07-26 17:20
 */
public class Consumer {

	public static void main(String[] args) throws Exception {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroup");
		consumer.setNamesrvAddr("localhost:9876");
		consumer.subscribe("Topic-Sync", "*");
		consumer.registerMessageListener((MessageListenerConcurrently) (msgs, consumeConcurrentlyContext) -> {
			System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		});
		consumer.start();

		System.out.printf("Consumer Started.%n");
	}
}
