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
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/userRegister")
public class UserRegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		long phone=Long.parseLong(request.getParameter("phone"));
		int userAdminId=Integer.parseInt(request.getParameter("userAdminId"));
		User user=new User(firstName,lastName,email,password,phone,userAdminId);
		IAccessService service=new AccessServiceImpl();
		boolean f=service.userRegister(user);
		HttpSession session = request.getSession();
		if(f) {
			session.setAttribute("userRegSuccess","Register Successful.");
			response.sendRedirect("user/user.jsp");
		}
		else {
			session.setAttribute("userRegFail","Could'nt Register.Please try again.");
			response.sendRedirect("user/userReg.jsp");
		}
		
	}

	
	}
