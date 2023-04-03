package com.library.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dto.Admin;

/**
 * Servlet Filter implementation class AdminAuthFilter
 */
@WebFilter("/admin/*")
public class AdminAuthFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AdminAuthFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		Admin admin = (Admin) session.getAttribute("admin");

		if (admin != null) {
			session.setAttribute("admin", admin);
			res.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
			res.setHeader("Pragma", "no-cache");
			res.setDateHeader("Expires", 0);
			chain.doFilter(request, response);
		} else
			res.sendRedirect("../admin.jsp");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
