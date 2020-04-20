package com.example.other.retry;

import com.example.other.utils.RetryUtils;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huangqi
 * @Package com.example.other.service.impl
 * @Description:
 * @date 2019-07-02 10:21
 */
@Service
public class RetryServiceImpl implements RetryService {
	public AtomicInteger count = new AtomicInteger(0);

	@Retryable(
			value = {Exception.class},
			maxAttempts = 2,
					backoff = @Backoff(delay = 5000, multiplier = 2))
	@Override
	public void testRetry() throws Exception {
		System.out.println("testRetry----" + count.getAndAdd(1));
		if (count.get() >= 0) {
			throw new Exception("抛出异常");
		}
	}


		@Recover
	public void recover(Exception e) {
		System.out.println(e.getMessage());
	}


	@Override
	public void testRetryTemplate() throws Exception {

		RetryUtils.simpleRetryOperate(3, 5, 1, (retryContext) -> {
			System.out.println("次数：" + retryContext.getRetryCount());
			if (retryContext.getRetryCount() >= 0) {
				throw new Exception("123");
			}
			return "123";
		}, (retryContext) -> {
			System.out.println("超过次数，恢复；次数：" + retryContext.getRetryCount());
			return "恢复调用";
		});

		System.out.println("异步测试");

	}

	public void retryTemplate() throws Exception {
		//模板
		RetryTemplate template = new RetryTemplate();
		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
		retryPolicy.setMaxAttempts(3);
		template.setRetryPolicy(retryPolicy);

		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setMultiplier(2);
		backOffPolicy.setInitialInterval(2000L);
		template.setBackOffPolicy(backOffPolicy);

		String execute = template.execute((retryContext) -> {
			System.out.println("次数：" + retryContext.getRetryCount());
			if (retryContext.getRetryCount() == 0) {
				throw new Exception("123");
			}
			return "123";
		}, (retryContext) -> {
			System.out.println("超过次数，恢复；次数：" + retryContext.getRetryCount());
			return "恢复调用";
		});

		System.out.println("返回：" + execute);
	}
}
