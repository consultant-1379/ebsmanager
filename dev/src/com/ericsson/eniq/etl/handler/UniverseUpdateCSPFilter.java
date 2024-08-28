package com.ericsson.eniq.etl.handler;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class UniverseUpdateCSPFilter implements Filter{
	
	private StringBuilder policy = new StringBuilder();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.policy.append("frame-ancestors 'self'");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse httpResponse = ((HttpServletResponse) response);
		httpResponse.setHeader("Content-Security-Policy", this.policy.toString());
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// This is invoked only once when filter is taken out of the service.

	}

}
