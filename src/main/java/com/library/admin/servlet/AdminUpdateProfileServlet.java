package com.library.admin.servlet;

import java.io.IOException;

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
 * Servlet implementation class AdminUpdateProfileServlet
 */
@WebServlet("/updateAdminProfile")
public class AdminUpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Admin admin=(Admin)session.getAttribute("admin");
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		long phone=Long.parseLong(request.getParameter("phone"));
		int userAdminId=Integer.parseInt(request.getParameter("userAdminId"));
		
		Admin updateAdmin=new Admin(admin.getAdminId(),firstName,lastName,email,admin.getPassword(),phone);
		IAdminService adminService=new AdminServiceImpl();
		boolean f=adminService.updateProfile(updateAdmin);
	
		if(f) {
			session.setAttribute("successMsg","Changes saved.");
			session.setAttribute("admin", updateAdmin);
		}
		else 
			session.setAttribute("errorMsg","Please try again after some time.");
			
		request.getRequestDispatcher("userProfile").forward(request, response);
		
	}
	}

