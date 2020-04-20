package com.example.other.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @author huangqi
 * @Package com.example.other.rocketmq
 * @Description:
 * @date 2019-07-27 12:35
 */
public class BroadcastConsumer {
	public static void main(String[] args)throws Exception {
		customerOne();
		customeTwo();
	}

	public static void customerOne()throws Exception{
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("BroadcastConsumerGroup1");

		consumer.setNamesrvAddr("127.0.0.1:9876");
		consumer.setMessageModel(MessageModel.BROADCASTING);
		consumer.subscribe("Order-Topic","*");
		consumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.println("ConsumerOne"+new String(msgs.get(0).getBody()));

				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});


		consumer.start();

		System.out.printf("ConsumerOne Started.%n");
	}
	public static void customeTwo()throws Exception{
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("BroadcastConsumerGroup2");
		consumer.setNamesrvAddr("127.0.0.1:9876");
		consumer.setMessageModel(MessageModel.BROADCASTING);
		consumer.subscribe("Order-Topic","*");
		consumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.println("ConsumerTwo:" +new String(msgs.get(0).getBody()));

				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});

		consumer.start();

		System.out.printf("ConsumerTwo Started.%n");
	}
}
