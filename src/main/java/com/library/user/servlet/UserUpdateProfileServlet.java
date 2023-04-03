package com.library.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dto.User;
import com.library.service.IUserService;
import com.library.service.UserServiceImpl;

/**
 * Servlet implementation class SaveChangesServlet
 */
@WebServlet("/updateUserProfile")
public class UserUpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		long phone=Long.parseLong(request.getParameter("phone"));
		int userAdminId=Integer.parseInt(request.getParameter("userAdminId"));
		
		User updateUser=new User(user.getUserId(),firstName,lastName,email,user.getPassword(),phone,userAdminId);
		IUserService userService=new UserServiceImpl();
		boolean f=userService.updateProfile(updateUser);
	
		if(f) {
			session.setAttribute("successMsg","Changes saved.");
			session.setAttribute("user", updateUser);
		}
		else 
			session.setAttribute("errorMsg","Please try again after some time.");
			
		request.getRequestDispatcher("userProfile").forward(request, response);
		
	}

	}
