package com.javadevmap.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadevmap.zuul.feigns.AuthFeign;
import com.javadevmap.zuul.result.Result;
import com.javadevmap.zuul.result.ResultCode;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class AuthFilter extends ZuulFilter{
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private AuthFeign feign;

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();  
        return (boolean) ctx.get("isFilterSuccess");
	}

	@Override
	public Object run() {
		try {
			RequestContext ctx = RequestContext.getCurrentContext();  
	        HttpServletRequest request = ctx.getRequest();  
	        String token = request.getHeader("token");
	        if(token!=null) {
	        	Result<String> authResult = feign.validToken(token);
	        	if(authResult.getResultCode() == 200) {
	        		ctx.setSendZuulResponse(true); 
		        	ctx.set("isFilterSuccess", true);
	        	}else {
	        		ctx.setSendZuulResponse(false);
	        		ctx.set("isFilterSuccess", false);
					ctx.setResponseStatusCode(401);
					Result<String> result = new Result<>(ResultCode.Unauthorized);
					ctx.setResponseBody(mapper.writeValueAsString(result));
	        	}
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
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
