package com.library.service;

import com.library.dao.AdminDao;
import com.library.dao.DbConnection;
import com.library.dao.UserDao;
import com.library.dto.Admin;
import com.library.dto.User;

public class AccessServiceImpl implements IAccessService {
	private AdminDao aDao = new AdminDao(DbConnection.jdbcConnection());
	private UserDao uDao = new UserDao(DbConnection.jdbcConnection());

	@Override
	public Admin adminLogin(String email, String password) {
		return aDao.adminLogin(email, password);
	}

	@Override
	public boolean adminRegister(Admin admin) {
		return aDao.adminRegister(admin);
	}

	@Override
	public User userLogin(String email, String password) {
		return uDao.userLogin(email, password);
	}

	@Override
	public boolean userRegister(User user) {
		return uDao.userRegister(user);
	}

}
