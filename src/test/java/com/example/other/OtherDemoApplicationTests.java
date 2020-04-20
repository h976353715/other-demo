package com.example.other;

import com.example.other.retry.RetryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OtherDemoApplicationTests {

	@Autowired
	private RetryService retryService;

	@Test
	public void contextLoads() throws Exception {
		//retryService.testRetry();
		retryService.testRetryTemplate();
	}

}
