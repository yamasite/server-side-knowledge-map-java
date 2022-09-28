package com.javadevmap.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLogbackAppFile {
	private static final Logger logger = LoggerFactory.getLogger(SpringLogbackAppFile.class);
	static ApplicationContext context = null;

	public static void main(String[] args) {
		// IoC获取beans的上下文
		context = new ClassPathXmlApplicationContext("spring-bean.xml");

		for (int i = 0; i < 20000; i++) {
			logger.debug("debug" + i);
			logger.info("test");
			logger.warn("warn " + i);
			logger.error("error " + i);
		}

	}
}
