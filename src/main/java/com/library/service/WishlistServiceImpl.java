package com.library.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.dao.WishlistDao;
import com.library.dto.Wishlist;
import com.library.util.Utilities;

public class WishlistServiceImpl implements IWishlistService {
	WishlistDao wishlistDao = new WishlistDao(Utilities.getConnection());

	@Override
	public boolean addToWishlist(int userId, int bookId) {
		return wishlistDao.addToWishList(userId, bookId);
	}

	@Override
	public Map<String, Object> displayWishlist(int userId, int pageNo, int maxBooks) {
		Map<String, Object> wishlistMap = new HashMap<>();
		int start = Utilities.findStart(maxBooks, pageNo);
		List<Wishlist> wishlist = wishlistDao.getAll(userId);
		int total = wishlist.size();
		if (total > maxBooks)
			wishlist = wishlist.subList(start, maxBooks);
		int noOfPage = Utilities.findTotalPages(maxBooks, total);
		wishlistMap.put("wishlist", wishlist);
		wishlistMap.put("noOfPage", noOfPage);
		return wishlistMap;
	}

	@Override
	public boolean removeWishlist(int wishlistId) {
		return wishlistDao.removeWishlist(wishlistId);
	}

}
