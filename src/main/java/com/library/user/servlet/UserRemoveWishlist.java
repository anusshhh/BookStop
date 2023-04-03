package com.library.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.service.IWishlistService;
import com.library.service.WishlistServiceImpl;

/**
 * Servlet implementation class UserRemoveWishlist
 */
@WebServlet("/removeWishlist")
public class UserRemoveWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserRemoveWishlist() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int wishlistId=Integer.parseInt(request.getParameter("id"));
		IWishlistService wishlistService=new WishlistServiceImpl();
		boolean f=wishlistService.removeWishlist(wishlistId);
		HttpSession session=request.getSession();
		if(!f)
			session.setAttribute("Please try again later.","removeFailed");
			
			request.getRequestDispatcher("wishlist").forward(request, response);
	}

}
