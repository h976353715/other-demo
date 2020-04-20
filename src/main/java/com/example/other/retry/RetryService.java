package com.example.other.retry;

/**
 * @author huangqi
 * @Package com.example.other.service
 * @Description: 重试机制
 * @date 2019-07-02 10:20
 */
public interface RetryService {

	void testRetry() throws Exception;
	void testRetryTemplate() throws Exception;
}
