package com.library.user.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
 * Servlet implementation class UserChangePasswordServlet
 */
@WebServlet("/userChangePassword")
public class UserChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		IUserService userService = new UserServiceImpl();
		User user = (User) session.getAttribute("user");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		Map<String, String> passwords = new HashMap<>();
		passwords.put("oldPassword", oldPassword);
		passwords.put("newPassword", newPassword);
		passwords.put("confirmPassword", confirmPassword);
		boolean f = userService.changePassword(passwords, user);
		if (f)
			session.setAttribute("passChanged", "Password changed successfully.");
		else
			session.setAttribute("passFailed", "Incorrect old password or new password did not match.");
		request.getRequestDispatcher("userProfile").forward(request, response);

	}
	}

