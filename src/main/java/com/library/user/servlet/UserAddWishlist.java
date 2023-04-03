package com.library.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dto.User;
import com.library.service.IWishlistService;
import com.library.service.WishlistServiceImpl;

/**
 * Servlet implementation class UserAddWishlist
 */
@WebServlet("/addWishlist")
public class UserAddWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddWishlist() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		int bookId=Integer.parseInt(request.getParameter("bookId"));
		IWishlistService wishlistService=new WishlistServiceImpl();
		boolean f=wishlistService.addToWishlist(user.getUserId(),bookId);
		
		if (f) {
			session.setAttribute("wishlistAdded","Added to wishlist.");
			request.getRequestDispatcher("wishlist").forward(request, response);
		}

		else {
			session.setAttribute("wishlistNotAdded","Try again later.");
		request.getRequestDispatcher("user/userWishlist.jsp").forward(request, response);
	}
	}
}
