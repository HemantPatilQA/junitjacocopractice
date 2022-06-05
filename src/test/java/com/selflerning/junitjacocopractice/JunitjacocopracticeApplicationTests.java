package com.selflerning.junitjacocopractice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JunitjacocopracticeApplicationTests {

	@Test
	void contextLoads() {
	}

	//Below is a dummy method just to improve the code coverage
	@Test
	public void main() {
		JunitjacocopracticeApplication.main(new String[] {});
	}

}
