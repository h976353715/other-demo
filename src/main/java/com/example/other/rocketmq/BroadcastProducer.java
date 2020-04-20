package com.example.other.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @author huangqi
 * @Package com.example.other.rocketmq
 * @Description:
 * @date 2019-07-27 12:35
 */
public class BroadcastProducer {

	public static void main(String[] args)throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer("BroadcastProdGroup");
		producer.setNamesrvAddr("127.0.0.1:9876");
		producer.start();
		String[] tags = {"TagA","TagB","TagC","TagD"};
		for (int i = 0; i <10 ; i++) {
			Message msg = new Message("Order-Topic",tags[i%tags.length],
					"key_"+i,("Hello This is OrderProducer "+i).getBytes());
			SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
				@Override
				public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
					System.out.println("arg: " + arg);
					Integer id = (Integer) arg;
					int index = id % mqs.size();
					return mqs.get(index);
				}
			}, 0);

			System.out.println(sendResult);

		}
		producer.shutdown();
	}
}
