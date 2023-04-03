package com.library.service;

import java.util.List;
import java.util.Map;

import com.library.dto.Admin;
import com.library.dto.User;

public interface IUserService {

	public int countUser(int adminId);

	public List<User> selectUser(int adminId);
	
	public boolean updateProfile(User user);
	
	public boolean changePassword(Map<String, String> passwords, User user);

}
