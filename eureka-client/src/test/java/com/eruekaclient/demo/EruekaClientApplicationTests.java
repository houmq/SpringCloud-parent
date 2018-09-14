package com.eruekaclient.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class EruekaClientApplicationTests {

	@Test
	public void contextLoads() {
		String[] str = new String[]{"a","v"};
		String s = str + "C";

		System.out.println(str.toString());
	}

}
