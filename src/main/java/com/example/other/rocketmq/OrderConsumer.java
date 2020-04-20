package com.example.other.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author huangqi
 * @Package com.example.other.rocketmq
 * @Description:
 * @date 2019-07-27 12:35
 */
public class OrderConsumer {
	public static void main(String[] args)throws Exception {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("OrderConsumerGroup");
		consumer.setNamesrvAddr("127.0.0.1:9876");
//		consumer.setMessageModel(MessageModel.);
		consumer.subscribe("Order-Topic","*");
		consumer.registerMessageListener(new MessageListenerOrderly() {
			AtomicLong consumeTimes = new AtomicLong(0);
			@Override
			public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
				context.setAutoCommit(false);
				//System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
				System.out.println(new String(msgs.get(0).getBody()));
				/*this.consumeTimes.incrementAndGet();
				if ((this.consumeTimes.get() % 2) == 0) {
					return ConsumeOrderlyStatus.SUCCESS;
				} else if ((this.consumeTimes.get() % 3) == 0) {
					return ConsumeOrderlyStatus.ROLLBACK;
				} else if ((this.consumeTimes.get() % 4) == 0) {
					return ConsumeOrderlyStatus.COMMIT;
				} else if ((this.consumeTimes.get() % 5) == 0) {
					context.setSuspendCurrentQueueTimeMillis(3000);
					return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
				}*/
				return ConsumeOrderlyStatus.SUCCESS;
			}
		});

		consumer.start();

		System.out.printf("Consumer Started.%n");
	}
}
