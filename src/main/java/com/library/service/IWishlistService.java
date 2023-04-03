package com.library.service;

import java.util.Map;

public interface IWishlistService {

	public boolean addToWishlist(int userId, int bookId);

	public Map<String,Object> displayWishlist(int userId,int pageNo,int maxBooks);

	public boolean removeWishlist(int wishlistId);

}
