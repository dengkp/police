package com.zz.police.common.xss;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * XSS过滤
 * @author dengkp
 */
public class XssFilter implements Filter {

	private FilterConfig filterConfig = null;

	private List<String> urlExclusion = null;

	@Override
	public void init(FilterConfig config) {
		this.filterConfig = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String servletPath = httpServletRequest.getServletPath();
		httpServletRequest.getParameterMap();
		if (urlExclusion != null && urlExclusion.contains(servletPath)) {
			chain.doFilter(request, response);
		} else {
			chain.doFilter(new XssHttpServletRequestWrapper(httpServletRequest), response);
		}
	}

	@Override
	public void destroy() {
		this.filterConfig = null;
	}

	public List<String> getUrlExclusion() {
		return urlExclusion;
	}

	public void setUrlExclusion(List<String> urlExclusion) {
		this.urlExclusion = urlExclusion;
	}

}