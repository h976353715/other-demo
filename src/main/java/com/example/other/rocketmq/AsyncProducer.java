package com.example.other.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author huangqi
 * @Package com.example.other.rocketmq
 * @Description: 异步生产者
 * @date 2019-07-26 17:15
 */
public class AsyncProducer {

	public static void main(String[] args) throws Exception{
		DefaultMQProducer producer = new DefaultMQProducer("ThisGroupName-AsyncProducer");
		//消息服务中心地址
		producer.setNamesrvAddr("127.0.0.1:9876");
		producer.start();
		final CountDownLatch countDownLatch = new CountDownLatch(5);
		producer.setRetryTimesWhenSendAsyncFailed(0);
		for (int i = 0; i < 5; i++) {
			Message ms = new Message("Topic-Sync","TagA","KEY-A","Hello!This is AsyncProducer.".getBytes());
			 producer.send(ms, new SendCallback() {
				 @Override
				 public void onSuccess(SendResult sendResult) {
					 System.out.println(sendResult);
				 }

				 @Override
				 public void onException(Throwable throwable) {
					 System.out.println(throwable);
				 }
			 });

		}
		countDownLatch.await(5, TimeUnit.SECONDS);
		producer.shutdown();
	}
}
