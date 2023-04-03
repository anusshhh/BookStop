package com.library.admin.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dto.Admin;
import com.library.service.AdminServiceImpl;
import com.library.service.IAdminService;

/**
 * Servlet implementation class AdminChangePasswordServlet
 */
@WebServlet("/adminChangePassword")
public class AdminChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminChangePasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		IAdminService adminService = new AdminServiceImpl();
		Admin admin = (Admin) session.getAttribute("admin");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		Map<String, String> passwords = new HashMap<>();
		passwords.put("oldPassword", oldPassword);
		passwords.put("newPassword", newPassword);
		passwords.put("confirmPassword", confirmPassword);
		boolean f = adminService.changePassword(passwords, admin);
		if (f)
			session.setAttribute("passChanged", "Password changed successfully.");
		else
			session.setAttribute("passFailed", "Incorrect old password or new password did not match.");
		request.getRequestDispatcher("adminProfile").forward(request, response);

	}
}
