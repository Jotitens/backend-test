package com.akka.backendtest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendTestApplicationTests {

	@Test
	void contextLoads() {
		BackendTestApplication.main(new String[] {"arg1", "arg2", "arg3"});
	}

}
