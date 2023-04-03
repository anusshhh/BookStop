package com.library.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dto.User;
import com.library.service.AccessServiceImpl;
import com.library.service.IAccessService;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/userLogin")
public class UserLoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		IAccessService service=new AccessServiceImpl();
		User user=service.userLogin(email, password);
		HttpSession session = request.getSession();
		if (user != null) {
			session.setAttribute("user", user);
			request.getRequestDispatcher("userLibrary").forward(request, response);
		} else {
			session.setAttribute("userLoginFail", "Invalid Credentials.");
			response.sendRedirect("user.jsp");
		}
	}

}
