package com.luizmoneyapi.cros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luizmoneyapi.config.property.LuizMoneyApiProperty;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CrosFilter implements Filter{
	
	@Autowired
	private LuizMoneyApiProperty luizMoneyApiProperty;
	

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest  request= (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		response.setHeader("Access-Control-Allow-Origin", luizMoneyApiProperty.getSeguranca().getOrgiemPermitida());
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		if ("OPTIONS".equals(request.getMethod()) && luizMoneyApiProperty.getSeguranca().getOrgiemPermitida().equals(request.getHeader("origin"))){
			response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
			response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
			response.setHeader("Access-Control-Max-Age", "3600");
		}else {
			chain.doFilter(req, resp);
		}		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}


	@Override
	public void destroy() {
		
	}

}
