package com.library.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.dao.BookDao;
import com.library.dao.HistoryDao;
import com.library.dto.Book;
import com.library.dto.User;
import com.library.util.Utilities;

/**
 * Implementation class for Book Service.
 * 
 * @author Anush Padubidri
 * 
 * @see IBookService
 *
 */
public class BookServiceImpl implements IBookService {
	private BookDao dao = new BookDao(Utilities.getConnection());
	private HistoryDao hDao = new HistoryDao(Utilities.getConnection());

	@Override
	public boolean addBook(Book book) {
		return dao.addBook(book);
	}

	@Override
	public Map<String, Object> displayBooks(int adminId, int pageNo, int maxBooks, String query) {
		int start = Utilities.findStart(maxBooks, pageNo), end;
		Map<String, Object> bookObj = new HashMap<>();
		List<Book> books = Collections.emptyList();
		if (query != null)
			books = dao.searchBooks(adminId, query);
		else
			books = dao.displayBook(adminId, "id");
		int total = books.size();
		if (total > (start + maxBooks))
			end = start + maxBooks;
		else
			end = total;
		books = books.subList(start, end);

		int noOfPage = Utilities.findTotalPages(maxBooks, total);

		bookObj.put("total", total);
		bookObj.put("books", books);
		bookObj.put("noOfPage", noOfPage);
		return bookObj;
	}

	@Override
	public void deleteBook(int bookId) {

	}

	@Override
	public boolean editBook(Book book) {
		return dao.editBook(book);
	}

	@Override
	public String getBookSummary(int bookId) {
		return dao.getSummary(bookId);
	}

	@Override
	public List<Book> recommendedBooks(User user) {
		int start = 0, maxBooks = 3, total = 0, end = 0;
		String favouriteCategory = hDao.favouriteCategory(user.getUserId());
		List<Book> books = dao.getBookByCategory(favouriteCategory, user.getUserAdminId());
		total = books.size();
		if (total > (start + maxBooks))
			end = start + maxBooks;
		else
			end = total;
		return books.subList(start, end);
	}

	@Override
	public Map<String, Object> getBookByCategory(String cat, int adminId, int maxBooks, int filterPageNo) {
		int end, total, start = Utilities.findStart(maxBooks, filterPageNo);
		Map<String, Object> bookMap = new HashMap<>();
		List<Book> books = Collections.emptyList();
		books = dao.getBookByCategory(cat, adminId);
		if (maxBooks < 0) {
			bookMap.put("books", books);
			return bookMap;
		}
		total = books.size();
		if (total > (start + maxBooks))
			end = start + maxBooks;
		else
			end = total;
		books = books.subList(start, end);
		int noOfPage = Utilities.findTotalPages(maxBooks, total);
		bookMap.put("books", books);
		bookMap.put("noOfPage", noOfPage);
		return bookMap;
	}

}
