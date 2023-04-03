package com.library.service;

import java.util.List;

import com.library.dto.Category;

public interface ICategoryService {
	/**
	 * Service to populate all the categories from System.
	 * 
	 * @param adminId Admin's ID whose library's category list is to be displayed.
	 * 
	 * @return A {@link List} of {@link Category}
	 */
	public List<Category> showCategory(int adminId);

}
