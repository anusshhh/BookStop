package com.library.user.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dto.Book;
import com.library.dto.Record;
import com.library.dto.User;
import com.library.dto.Wishlist;
import com.library.service.BookServiceImpl;
import com.library.service.IBookService;
import com.library.service.IRecordService;
import com.library.service.IWishlistService;
import com.library.service.RecordServiceImpl;
import com.library.service.WishlistServiceImpl;

/**
 * Servlet implementation class UserDisplayWishlist
 */
@WebServlet("/wishlist")
public class UserWishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int maxBooks=18;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		IBookService bookService = new BookServiceImpl();
		IRecordService recordService = new RecordServiceImpl();
		IWishlistService wishlistService = new WishlistServiceImpl();
		int pageNo = (request.getParameter("pageNo") == "" || (request.getParameter("pageNo") == null)) ? 0
				: Integer.parseInt(request.getParameter("pageNo"));
		int userId = user.getUserId();
		Record record = recordService.getRecordByUser(userId).get(0);
		Map<String,Object> wishlistMap = wishlistService.displayWishlist(userId,pageNo,maxBooks);
		List<Wishlist> wishlist=(List<Wishlist>) wishlistMap.get("wishlist");
		int noOfPage=(int) wishlistMap.get("noOfPage");
		List<Book> recommendedBooks = bookService.recommendedBooks(user);
		session.setAttribute("wishlist", wishlist);
		session.setAttribute("record", record);
		session.setAttribute("recommendedBooks", recommendedBooks);
		session.setAttribute("pageNo", pageNo);
		session.setAttribute("noOfPage",noOfPage);		
		response.sendRedirect("user/userWishlist.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
