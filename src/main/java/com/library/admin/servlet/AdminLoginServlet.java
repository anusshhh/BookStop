package com.library.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dao.AdminDao;
import com.library.dto.Admin;
import com.library.service.AccessServiceImpl;
import com.library.service.IAccessService;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		IAccessService service=new AccessServiceImpl();
		Admin admin=service.adminLogin(email, password);
		HttpSession session=request.getSession();
		if(admin!=null) {
			session.setAttribute("admin",admin);
			request.getRequestDispatcher("dashboard").forward(request, response);;
		}
		else {
			session.setAttribute("adminLoginFail", "Invalid Credentials.");
			response.sendRedirect("admin.jsp");
		}
	}

}
