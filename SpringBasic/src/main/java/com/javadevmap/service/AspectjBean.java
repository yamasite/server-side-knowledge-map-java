package com.javadevmap.service;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectjBean {

	@Pointcut("execution(* com.javadevmap.service.ProductService.*(..))") // expression
	private void businessService() {
	}
	@Before("businessService()")
	public void beforeAdvice() {
		System.out.println("beforeAdvice() --> Going to exec addProduct.");
	}
	@After("businessService()")
	public void afterAdvice() {
		System.out.println("afterAdvice() --> addProduct has been done.");
	}
	@AfterReturning(pointcut = "businessService()", returning = "retVal")
	public void afterReturningAdvice(Object retVal) {
		System.out.println("afterReturningAdvice() -->Returning");
	}
	@AfterThrowing(pointcut = "businessService()", throwing = "ex")
	public void AfterThrowingAdvice(IllegalArgumentException ex) {
		System.out.println("AfterThrowingAdvice--> There has been an exception: " + ex.toString());
	}
}
