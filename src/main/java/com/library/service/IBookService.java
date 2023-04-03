package com.library.service;

import java.util.List;
import java.util.Map;

import com.library.dto.Book;
import com.library.dto.User;

/**
 * Book Service caters for add/edit/remove/search Books.
 * 
 * @author Anush Padubidri
 * 
 * @version 1.0
 *
 */
public interface IBookService {
	
	/**
	 * Service to add a book.
	 * 
	 * @param book An instance of {@link Book} contains book data.
	 * 
	 * @return {@code true} if book is added
	 */
	public boolean addBook(Book book);
	
	/**
	 * Service to display all the books from System.
	 * 
	 * @param pageNo page number at which data is to be displayed.
	 * 
	 * @return A {@link List} of {@link Book}
	 */
	public Map<String, Object> displayBooks(int adminId,int pageNo,int maxBooks,String query);
	/**
	 * Service to delete a {@link Book} from System.
	 * 
	 * 
	 */
	public void deleteBook(int bookId);
	/**
	 * Service to edit a {@link Book} from System.
	 */
	public boolean editBook(Book book);
	
	public String getBookSummary(int bookId);
	
	public List<Book> recommendedBooks(User user);
	
	public Map<String,Object> getBookByCategory(String cat, int userId,int maxBooks,int filterPageNo);
	 
}
