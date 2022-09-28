package com.javadevmap.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadevmap.zuul.result.Result;
import com.javadevmap.zuul.result.ResultCode;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class CommonFilter extends ZuulFilter{

	ObjectMapper mapper = new ObjectMapper();
	@Override
	public Object run() {
		try {
			RequestContext ctx = RequestContext.getCurrentContext();  
	        HttpServletRequest request = ctx.getRequest();  
	        String versionString = request.getHeader("version");
	        String typeString = request.getHeader("clienttype");
	        if(versionString!=null && typeString!=null) {
	        	ctx.setSendZuulResponse(true); 
	        	ctx.set("isFilterSuccess", true);
	        }else {
				ctx.setSendZuulResponse(false);
				ctx.set("isFilterSuccess", false);
				ctx.setResponseStatusCode(400);
				Result<String> result = new Result<>(ResultCode.Bad_Request);
				ctx.setResponseBody(mapper.writeValueAsString(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestContext ctx = RequestContext.getCurrentContext();  
			ctx.setSendZuulResponse(false);
			ctx.set("isFilterSuccess", false);
			ctx.setResponseStatusCode(400);
		}
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
