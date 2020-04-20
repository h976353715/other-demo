package com.example.other.utils;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author huangqi
 * @Package com.example.other
 * @Description: 重试操作 方法类
 * @date 2019-07-05 09:31
 */
public class RetryUtils {

	/**
	 * 简单的重试机制(非异步)
	 *
	 * @param maxRetryCount    最大重试次数
	 * @param initSecond       初始重试间隔时间
	 * @param multiplier       倍率 设置为1则是相等时间间隔重试
	 * @param retryCallback    重试的逻辑（只有抛出异常才会重试）
	 * @param recoveryCallback 重试次数没成功后的逻辑
	 * @param <T>
	 * @param <E>
	 * @return
	 * @throws E
	 */
	public static <T, E extends Throwable> T simpleRetryOperate(int maxRetryCount, int initSecond, int multiplier,
																RetryCallback<T, E> retryCallback, RecoveryCallback<T> recoveryCallback) throws E {
		//模板
		RetryTemplate template = new RetryTemplate();
		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
		retryPolicy.setMaxAttempts(maxRetryCount);
		template.setRetryPolicy(retryPolicy);

		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setMultiplier(multiplier);
		backOffPolicy.setInitialInterval(initSecond * 1000L);
		template.setBackOffPolicy(backOffPolicy);
		T executeResult = template.execute(retryCallback, recoveryCallback);
		return executeResult;
	}

}
