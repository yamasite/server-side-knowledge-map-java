package com.javadevmap.interceptor;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求耗时统计拦截器
 */
public class HandlerInterceptorTimeConsume implements HandlerInterceptor {

	private NamedThreadLocal<Long> threadLocal = new NamedThreadLocal<Long>("execConsumeTime");

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		System.out.println("Thread.currentThread().getId() = " + Thread.currentThread().getId());
		long beginTime = System.currentTimeMillis(); // 开始时间
		threadLocal.set(beginTime);
		return true;
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {

		long endTime = System.currentTimeMillis();
		long consumeMills = endTime - threadLocal.get();
		System.out.println(String.format("请求RequestURI: %s ; method = %s  -> 耗时（毫秒）%s ", request.getRequestURI(),
				request.getMethod(), consumeMills));
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

}
