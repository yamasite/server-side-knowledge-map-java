package com.javadevmap.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestJunit {

	String code = "Hello World";
	ServiceOfBusiness service = new ServiceOfBusiness(code,false);

	@Test
	public void testPrintMethods() {
		assertEquals(code, service.printServerCode());
		assertEquals(false, service.printFlag());
	}
}