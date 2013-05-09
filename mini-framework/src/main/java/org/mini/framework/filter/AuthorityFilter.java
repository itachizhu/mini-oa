/**
 * 
 */
package org.mini.framework.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mini.framework.service.SessionService;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author Administrator
 * 
 */
public class AuthorityFilter implements Filter {

	/* session的key值 */
	private String sessionKey = null;
	/* 进行过滤的url列表 */
	private Set<String> filterUrlList = new HashSet<String>();
	/* 跳转的url */
	private String redirectURL = null;

	private SessionService sessionService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		filterUrlList.clear();
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		if (sessionKey == null) {
			filterChain.doFilter(request, response);
			return;
		}

		try {
			sessionService = (SessionService) WebApplicationContextUtils
					.getRequiredWebApplicationContext(
							request.getSession().getServletContext()).getBean(
							"sessionService");

			if (sessionService == null) {
				System.out.println("sessionService1注入失败。");
				filterChain.doFilter(request, response);
				return;
			} else  {
				System.out.println("sessionService1注入成功。");
			}

			if (sessionService.checkAuthority(request, response, sessionKey,
					filterUrlList, redirectURL))
				filterChain.doFilter(servletRequest, servletResponse);
		} catch (Exception e) {
			e.printStackTrace();
			filterChain.doFilter(servletRequest, servletResponse);
		}

		/*
		 * // 非ajax请求 if(request.getHeader("X-Requested-With") == null &&
		 * session.getAttribute(sessionKey) == null) {
		 * response.sendRedirect(redirectURL); return; }
		 * 
		 * 
		 * filterChain.doFilter(servletRequest, servletResponse);
		 */
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		redirectURL = filterConfig.getInitParameter("RedirectURL");
		sessionKey = filterConfig.getInitParameter("SessionKey");
		String filterUrlListStr = filterConfig
				.getInitParameter("FilterUrlList");
		if (filterUrlListStr != null) {
			// System.out.println(filterUrlListStr);
			String[] params = filterUrlListStr.split(",");
			for (int i = 0; i < params.length; i++) {
				filterUrlList.add(params[i].trim());
			}
		}
	}

}
