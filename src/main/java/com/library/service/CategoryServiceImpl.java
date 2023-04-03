package com.library.service;

import java.util.List;

import com.library.dao.CategoryDao;
import com.library.dto.Category;
import com.library.util.Utilities;

public class CategoryServiceImpl implements ICategoryService {
    CategoryDao dao=new CategoryDao(Utilities.getConnection());
	@Override
	public List<Category> showCategory(int adminId) {
		return dao.selectCategory(adminId);
	}

}
