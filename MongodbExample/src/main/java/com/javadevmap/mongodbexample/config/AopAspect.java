package com.javadevmap.mongodbexample.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Aspect
public class AopAspect {
	private static final Logger log = LoggerFactory.getLogger(AopAspect.class);
	ObjectMapper mapper = new ObjectMapper();
	
	@Pointcut("execution(* com.javadevmap.mongodbexample.controllers..*.*(..))")
	public void AopPointCut() {
		
	}

	@Before(value="AopPointCut()")
	public void AopBefore(JoinPoint point) {
		try {
			StringBuilder builder = new StringBuilder();
			builder.append(point.getSignature().getDeclaringTypeName());
			builder.append(" method = ");
			builder.append(point.getSignature().getName());
			builder.append(" args = ");
			for(Object object : point.getArgs()) {
				builder.append(mapper.writeValueAsString(object));
			}
			log.info(builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@AfterReturning(value="AopPointCut()",returning="ret")
	public void AopAfterReturning(JoinPoint point,Object ret) {
		try {
			StringBuilder builder = new StringBuilder();
			builder.append(point.getSignature().getDeclaringTypeName());
			builder.append(" method = ");
			builder.append(point.getSignature().getName());
			builder.append(" args = ");
			for(Object object : point.getArgs()) {
				builder.append(mapper.writeValueAsString(object));
			}
			builder.append(" ret = ");
			builder.append(mapper.writeValueAsString(ret));
			log.info(builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
