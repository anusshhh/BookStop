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

import com.library.dto.User;

/**
 * Servlet Filter implementation class UserAuthFilter
 */
@WebFilter("/user/*")
public class UserAuthFilter implements Filter {

	public UserAuthFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		User user = (User) session.getAttribute("user");

		if (user != null) {
			session.setAttribute("user", user);
			res.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
			res.setHeader("Pragma", "no-cache");
			res.setDateHeader("Expires", 0);
			chain.doFilter(request, response);
		} else
			res.sendRedirect("../user.jsp");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
