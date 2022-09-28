package com.javadevmap.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import com.javadevmap.domain.ResultBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 异常解析器
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	protected static final String DEFAULT_ERROR_MESSAGE = "系统忙，请稍后再试";

	/**
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 *            系统抛出的异常
	 * @return
	 */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		String errorMsg = ex instanceof CustomException ? ex.getMessage() : DEFAULT_ERROR_MESSAGE;
		String errorStack = Throwables.getStackTraceAsString(ex);

		System.out.printf("Request: %s raised %s", request.getRequestURI(), errorStack);

		ModelAndView modelAndView = null;

		//// 如果是ajax请求响应头会有x-requested-with
		if (request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			modelAndView = handleAjaxError(response, errorMsg);
		} else {
			// 非ajax请求时，session失效的处理
			modelAndView = handleViewError(request.getRequestURI(), ex.getMessage(), "error");
		}

		return modelAndView;

	}

	protected ModelAndView handleViewError(String url, String errorMessage, String viewName) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", url);
		// 将错误信息传到页面
		mav.addObject("message", errorMessage);
		// 指向错误页面
		mav.setViewName(viewName);
		return mav;
	}

	protected ModelAndView handleAjaxError(HttpServletResponse rsp, String errorMessage) {
		try {
			rsp.setCharacterEncoding("UTF-8");
			rsp.setContentType("application/json;charset=utf-8");
			rsp.setStatus(HttpStatus.OK.value());
			PrintWriter writer = rsp.getWriter();
			ResultBean bean = ResultBean.ofFail(500, errorMessage);
			ObjectMapper mapper = new ObjectMapper();
			// ResultBean类转JSON
			String json = mapper.writeValueAsString(bean);
			System.out.println(json);

			writer.write(json);// 输出
			writer.flush();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
