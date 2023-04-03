package com.library.service;

import java.util.Map;

import com.library.dao.AdminDao;
import com.library.dto.Admin;
import com.library.util.Utilities;

public class AdminServiceImpl implements IAdminService {

	private AdminDao adminDao = new AdminDao(Utilities.getConnection());

	@Override
	public boolean updateProfile(Admin admin) {
		return adminDao.updateProfile(admin);
	}

	@Override
	public boolean changePassword(Map<String, String> passwords, Admin admin) {
		String oldPassword = passwords.get("oldPassword");
		String newPassword = passwords.get("newPassword");
		String confirmPassword = passwords.get("confirmPassword");

		if ((admin.getPassword().equals(oldPassword)) && (newPassword.equals(confirmPassword))) {
			admin.setPassword(newPassword);
			return adminDao.updateProfile(admin);
		}
		return false;
	}

}
