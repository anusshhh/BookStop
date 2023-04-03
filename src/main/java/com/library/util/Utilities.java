package com.library.util;

import java.sql.Connection;

import com.library.dao.DbConnection;

public class Utilities {
	
	private Utilities() {
		
	}
	private static final Connection CONNECTION=DbConnection.jdbcConnection();
	
    public static Connection getConnection() {
    	return CONNECTION;
    }
	
	/**
	 * To compute the starting index element of the displaying list.
	 * 
	 * @param end - number of elements to be displayed.
	 * 
	 * @param pageNo - current page.
	 * 
	 * @return Starting index element of the displaying list.
	 */
	public static int findStart(int end,int pageNo) {

		if (pageNo > 0) {
			return pageNo * end;
		}
		return 0;
	}
	
	/**
	 * To compute the total number of pages to be displayed.
	 * 
	 * @param end - number of elements to be displayed.
	 * 
	 * @param total - total number of elements in the list.
	 * 
	 * @return Starting index element of the displaying list.
	 */
	
	public static int findTotalPages(int end,int total) {
		return ((int) Math.ceil(total * 1.0 / end));
	}
	

}
