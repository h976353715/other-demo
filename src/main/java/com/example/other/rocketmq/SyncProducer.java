package com.example.other.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author huangqi
 * @Package com.example.other.rocketmq
 * @Description: &#x540c;&#x6b65;&#x751f;&#x4ea7;&#x8005;
 * @date 2019-07-26 17:05
 */
public class SyncProducer {
	public static void main(String[] args) throws Exception {
		DefaultMQProducer defaultMQProducer = new DefaultMQProducer("ThisGroupName-SyncProducer");
		//消息服务中心地址
		defaultMQProducer.setNamesrvAddr("127.0.0.1:9876");
		defaultMQProducer.start();
		for (int i = 0; i < 5; i++) {
			Message ms = new Message("Topic-Sync","TagA","Hello!This is SyncProducer.".getBytes());
			SendResult sendResult = defaultMQProducer.send(ms);
			System.out.println(sendResult);
		}
		defaultMQProducer.shutdown();
	}

}
