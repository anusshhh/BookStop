package com.library.service;

import java.util.List;
import java.util.Map;

import com.library.dao.UserDao;
import com.library.dto.Admin;
import com.library.dto.User;
import com.library.util.Utilities;

public class UserServiceImpl implements IUserService {
	UserDao userDao = new UserDao(Utilities.getConnection());

	@Override
	public int countUser(int adminId) {
		return userDao.countUser(adminId);
	}
	
	@Override
	public List<User> selectUser(int adminId) {
		return userDao.selectUser(adminId);
	}

	@Override
	public boolean updateProfile(User user) {
		return userDao.updateProfile(user);
	}
	
	@Override
	public boolean changePassword(Map<String, String> passwords, User user) {
		String oldPassword = passwords.get("oldPassword");
		String newPassword = passwords.get("newPassword");
		String confirmPassword = passwords.get("confirmPassword");

		if ((user.getPassword().equals(oldPassword)) && (newPassword.equals(confirmPassword))) {
			user.setPassword(newPassword);
			return userDao.updateProfile(user);
		}
		return false;
	}
	
	
}
